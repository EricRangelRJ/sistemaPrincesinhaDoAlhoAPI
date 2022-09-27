package br.com.princesinhadoalho.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {

	// converte uma data do tipo String numa data do tipo Date
	public static Date toDate(String date) {
		
		// caso seja vazio
		if(date == null) {
			return null;
		}
		// receber uma data no padrão YYYY-MM-DD
		int ano = Integer.parseInt(date.substring(0, 4));
		int mes = Integer.parseInt(date.substring(5, 7));
		int dia = Integer.parseInt(date.substring(8, 10)); 

		//Calendar cal = new GregorianCalendar(ano, mes - 1, dia);
		Calendar cal = new GregorianCalendar(ano, mes -1,dia);
		return cal.getTime();
	}

	// converte uma data do tipo Date numa String
	public static String toString(Date data) {
		
		// caso seja vazio
		if (data == null) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(data);
	}

	// converte uma data do tipo Date numa String padrão BR
	public static String toStringPtBR(Date data) {

		// caso seja vazio
		if (data == null) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}
}

