package asiantech.internship.summer.java_core.ex04;

import java.util.List;
import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class PrimeFactor {

	public static void main(String[] args) {
		Logger log = Logger.getLogger(PrimeFactor.class.getName());
		int number = Common.input("Nhập vào n: ");
		List<String> listPrime = Common.primeFactor(number);
		log.info(Common.joinString("x", listPrime.toArray()));
	}
}
