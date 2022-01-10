package functionals.validators;

import java.util.Date;

/**
 * This class validates content stored in containers.
 * 
 * <p>
 * This class ensures the content stored in the container
 * classes is valid and ready to be stored in the database.
 * </p>
 * 
 * @version 1.0.0
 * @author David Bogdan
 * @since 1.0.0
 */
public final class ContentValidator {

	/**
	 * Checks if a specified CIF code is valid
	 * 
	 * @param	CIF		The CIF code to be tested
	 * @return	True or False denoting the validity of the code.
	 */
	public boolean isValidCIF(String CIF) {
		//1819101
		
		String digits = StripToDigits(CIF);
		long NumCIF, ControlNumber;
		int ControlDigit, Sum;
		
		if(digits == "" || digits.length() > 10)
			return false;
		NumCIF = Long.parseLong(digits);
		ControlNumber = 753217532L;
		Sum = 0;
		ControlDigit = CIF.charAt(CIF.length() - 1) - '0';
		NumCIF = NumCIF / 10;
		
		while(NumCIF > 0) {
			Sum += (NumCIF % 10) * (ControlNumber % 10);
			NumCIF /= 10;
			ControlNumber /= 10;
		}
		
		/*
		 * sum = 1 * 2 = 2
		 * sum = 0 * 3 = 2
		 * sum = 1 * 5 = 7
		 * sum = 9 * 7 = 70
		 * sum = 1 * 1 = 71
		 * sum = 8 * 2 = 87
		 * sum = 1 * 3 = 90
		 * 
		 * sum = 2
		 */
		
		Sum = Sum % 11;
		
		if(Sum == 10)
			Sum = 1;
		
		return (Sum == ControlDigit);
	}
	
	private String StripToDigits(String data) {
		String Digits = "0123456789";
		StringBuffer buffer = new StringBuffer();
		
		for(char c : data.toCharArray()) {
			for(char digit : Digits.toCharArray()) {
				if(c == digit) {
					buffer.append(c);
					break;
				}
			}
		}
		
		return buffer.toString();
	}
	
	/**
	 * Checks if a specified CNP code is valid
	 * 
	 * @param	CNP		The CNP code being tested
	 * @return	True or False denoting the validity of the code.
	 */
	@SuppressWarnings("deprecation")
	public boolean isValidCNP(String CNP) {
		int Year, Month, Day, ControlDigit, Sum;
		long NumCode, ControlNumber;
		String Digits = StripToDigits(CNP);
		
		if(Digits.length() == 13 && Digits.charAt(0) != '0') {
			
			//
			//	Date validation
			//
			
			Year = (Digits.charAt(1) - '0') * 10 + (Digits.charAt(2) - '0');
			Month = (Digits.charAt(3) - '0') * 10 + (Digits.charAt(4) - '0');
			Day = (Digits.charAt(5) - '0') * 10 + (Digits.charAt(6) - '0');
			
			switch(Digits.charAt(0)) {
			case '1':
			case '2':
				Year += 1900;
				break;
			case '3':
			case '4':
				Year += 1800;
				break;
			case '5':
			case '6':
				Year += 2000;
				break;
			default:
				Year += 1900;
				break;
			}
			
			try {
				Date dummy = new Date();
				
				dummy.setDate(Day);
				dummy.setMonth(Month);
				dummy.setYear(Year);
			} catch(Exception e) {
				return false;
			}
			
			NumCode = Long.parseLong(Digits);
			ControlNumber = 279146358279L;
			ControlDigit = Digits.charAt(12) - '0';
			NumCode = NumCode / 10;
			Sum = 0;
			
			while(NumCode > 0) {
				Sum += (NumCode % 10) * (ControlNumber % 10);
				NumCode /= 10;
				ControlNumber /= 10;
			}
			
			Sum = Sum % 11;
			if(Sum == 10 ) {
				Sum = 1;
			}
			
			if(Sum == ControlDigit)
				return true;
		}
		
		
		return false;
	}
	
	public ContentValidator() {
		// TODO Auto-generated constructor stub
	}

}
