package margincall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.AbstractMap.SimpleEntry;
import opennlp.tools.sentdetect.SentenceDetectorME;
import mailData.MarginCommunicationEntity;
import java.lang.Math;

public class MarginDataExtraction {

	Set<String> fullKeywords;

	Set<String> agreementKeywords;

	Set<String> partialKeywords;

	Set<String> disagreementKeywords;

	Set<String> negationKeywords;

	Set<String> currencyKeywords;

	Set<String> companyKeywords;

	Set<String> paymentKeywords;
	
	Set<String> notPaymentKeywords;

	SentenceDetectorUtil sentenceDetectorutil;

	TokenizerUtil tokeniserutil;

	class StatusResponse {
		String agreement_Status;
		String acceptedCallValue;
		String callValue;

		public StatusResponse() {
			// TODO Auto-generated constructor stub
			agreement_Status = "";
			acceptedCallValue = "";
			callValue = "";
		}

		public boolean MatchAcceptedCallValue() {
			if (acceptedCallValue == "" || callValue == "") {
				agreement_Status = "";
				return false;
			}

			int accCV = Integer.valueOf(acceptedCallValue.replaceAll("[,.]", ""));
			int CV = Integer.valueOf(callValue.replaceAll("[,.\\s]", ""));

			if (accCV == 0) {
				agreement_Status = "Full Disagreement";
			} else if (accCV == CV) {
				agreement_Status = "Full Agreement";
			} else {
				agreement_Status = "Partial Agreement";
			}

			return true;
		}
		
		public boolean AcceptedCVfromStatus() {
			if(agreement_Status == "Full Agreement"){
				acceptedCallValue = callValue;
				return true;
			}
			else if(agreement_Status == "Full Disagreement"){
				acceptedCallValue = "0";
				return true;
			}
			else{
				return false;
			}
		}

	}

	public MarginDataExtraction() {
		// TODO Auto-generated constructor stub

		fullKeywords = new HashSet<String>(Arrays.asList("full", "fully", "complete", "completely", "100%"));

		agreementKeywords = new HashSet<String>(Arrays.asList("agree", "agrees", "agreement", "agreeable", "accept", "accepted",
				"agreed", "concurrence", "concurrent", "accord", "NOT_dispute", "NOT_disputed", "NOT_disagreement",
				"NOT_disagree", "NOT_disagreed"));

		partialKeywords = new HashSet<String>(Arrays.asList("NOT_full", "NOT_fully", "NOT_complete", "NOT_completely",
				"NOT_100%", "partial", "partially", "marginally", "partly", "part"));

		disagreementKeywords = new HashSet<String>(Arrays.asList("dispute", "disputed", "disagreement", "disagree",
				"disagreed", "disagreeable", "NOT_agree", "NOT_agreement", "NOT_accept", "NOT_accepted", "NOT_agreed",
				"NOT_concurrence", "NOT_concurrent", "NOT_accord", "NOT_agreeing"));

		negationKeywords = new HashSet<String>(Arrays.asList("no", "not", "don't", "never", "dont"));

		currencyKeywords = new HashSet<String>(
				Arrays.asList("EUR", "USD", "INR", "AED", "AUD", "GBP", "Dollar", "dollar", "Euro", "euro"));

		companyKeywords = new HashSet<String>(Arrays.asList("ltd", "international", "co.", "bank"));

		paymentKeywords = new HashSet<String>(Arrays.asList("pay", "deliver", "hand over", "give", "render", "settle"));
		
		notPaymentKeywords = new HashSet<String>(Arrays.asList("NOT_pay", "NOT_deliver", "NOT_hand NOT_over", "NOT_give", "NOT_render", "NOT_settle"));
		

		sentenceDetectorutil = new SentenceDetectorUtil(
				"/Users/naunidhsingh/Documents/workspace/MarginCall/binFiles/en-sent.bin");

		tokeniserutil = new TokenizerUtil("/Users/naunidhsingh/Documents/workspace/MarginCall/binFiles/en-token.bin");

	}

	public String preprocessBodyTagString(String mailbody) {

		mailbody = mailbody.replaceAll("&nbsp;", " ");

		mailbody = mailbody.replaceAll("<([0-9A-Za-z\":,_%;!().{}#=-]|/|\\s)+>", "\n");

		mailbody = mailbody.replaceAll("</([0-9A-Za-z\":,_%;!().{}#=-]|/|\\s)+>", "\n");

		mailbody = mailbody.replaceAll("(\n)+", "\n");

		 System.out.println(mailbody);

		return mailbody;

	}

	private String getAgreementStatus(String[] listoflines) {

		String FinalStatus = "", _full = "", _agree = "";

		for (String rawline : listoflines) {

			String[] lines = sentenceDetectorutil.getSentences(rawline);

			for (String line : lines) {

				// System.out.println(line);

				line = line.toLowerCase();

				String[] words = line.split(" ");

				for (int i = 0; i < words.length; i++) {

					if (negationKeywords.contains(words[i])) {

						for (int j = i + 1; j < Math.min(words.length, i + 3); j++) {
							words[j] = "NOT_" + words[j];
						}

						break;

					}

				}

				boolean Status_partial = false, Status_full = false, Status_agree = false, Status_disagree = false,
						Status_payment = false;

				for (int i = 0; i < words.length; i++) {

					if (fullKeywords.contains(words[i])) {
						Status_full = true;
					}
					if (partialKeywords.contains(words[i])) {
						Status_partial = true;
					}
					if (agreementKeywords.contains(words[i])) {
						Status_agree = true;
					}
					if (disagreementKeywords.contains(words[i])) {
						Status_disagree = true;
					}
					if (paymentKeywords.contains(words[i])) {
						Status_payment = true;
					}

				}

				if (Status_full || Status_partial || Status_agree || Status_disagree) {

					if (Status_full)
						_full = "Full";
					else if (Status_partial)
						_full = "Partial";

					if (Status_agree || Status_payment)
						_agree = "Agreement";
					else if (Status_disagree)
						_agree = "Disagreement";

					FinalStatus = _full + " " + _agree;

					break;
				}

				// for(String word : words){
				//
				// System.out.println(word);
				//
				// }
				// System.out.println("-------");
			}

			if (rawline.contains("From:"))
				break;

		}

		return FinalStatus;

	}

	/**
	 * 
	 * @param listoflines
	 * @param callValue
	 * @return
	 */

	private String[] getNegationException(String line) {

		line = line.toLowerCase();
		String[] words = tokeniserutil.getLearnableTokenizer().tokenize(line);
		// Negation Check
		for (int i = 0; i < words.length; i++) {
			if (negationKeywords.contains(words[i])) {
				for (int j = i + 1; j < words.length; j++) {
					words[j] = "NOT_" + words[j];
					if (words[j].contains(","))
						break;
				}
				break;
			}
		}
		return words;

	}

	private StatusResponse findStatusWithWordList(String[] words, Set<String> keywordList, StatusResponse statusResponse, String regexStrng){
		
		for(int i = 0 ; i < words.length ; i++ ){
			
			if(keywordList.contains(words[i]) || keywordList.contains(words[i] + "s")){
				for(int j = i + 1 ; j < words.length ; j++){
					Matcher match = Pattern.compile(regexStrng).matcher(words[j]);
					try {
						while (match.find()) {
							statusResponse.acceptedCallValue = flushKMdenominations(match.group());
							return statusResponse;
						}
					} catch (Exception e) {
						continue;
					}
				}
				break;
			}
		}
		return statusResponse;
	}
		
	private String getRefId(String[] listoflines){
		
		String refid = "";

		for(String line: listoflines){
			
			if(line.contains("Ref:")){
				Matcher match = Pattern.compile("(?<=\\[)[A-Za-z0-9-]{34,42}(?=\\])").matcher(line);
				try {
					while (match.find()) {
						refid = match.group();
						return refid;
					}
					break;
				} catch (Exception e) {
					continue;
				}
			}
			
		}
		
		return refid;
		
	}
		
	
	private StatusResponse getAgreementStatusAndAcceptedCallValue(String[] listoflines, String callValue) {

		StatusResponse statusResponse = new StatusResponse();

		statusResponse.callValue = callValue;
		
		for (String rawline : listoflines) {

			if (rawline.contains("From:"))
				break;

			String[] lines = sentenceDetectorutil.getSentences(rawline);

			for (String line : lines) {

				// System.out.println(line);

				String[] words = getNegationException(line);

				// Search for Payment Keywords
				statusResponse = findStatusWithWordList(words, paymentKeywords, statusResponse, "[.,0-9][.,0-9]+(k|K|m|M)?");
				
				if (!statusResponse.MatchAcceptedCallValue()) {
					// Search for Agreement Keywords
					statusResponse = findStatusWithWordList(words, agreementKeywords, statusResponse, "[.,0-9][.,0-9][.,0-9]+(k|K|m|M)?");
					if(statusResponse.MatchAcceptedCallValue())
						return statusResponse;
				}
				else{
					return statusResponse;
				}
			}
		}


		for (String rawline : listoflines) {

			if (rawline.contains("From:"))
				break;

			String[] lines = sentenceDetectorutil.getSentences(rawline);

			for (String line : lines) {

				// System.out.println(line);

				String[] words = getNegationException(line);
				
				for(String word : words ){
					
					if(paymentKeywords.contains(word) || agreementKeywords.contains(word)){
						boolean partialFlag = false;
						for(String word2 : words ){
							if(partialKeywords.contains(word2)){
								statusResponse.agreement_Status = "Partial Agreement";
								partialFlag = true;
								return statusResponse;
							}
						}
						if(!partialFlag){
							statusResponse.agreement_Status = "Full Agreement";
							statusResponse.AcceptedCVfromStatus();
							return statusResponse;
						}
					}
					
					if(notPaymentKeywords.contains(word) || disagreementKeywords.contains(word)){
						boolean partialFlag = false;
						for(String word2 : words ){
							if(partialKeywords.contains(word2)){
								statusResponse.agreement_Status = "Partial Agreement";
								partialFlag = true;
								return statusResponse;
							}
							if(!partialFlag){
								statusResponse.agreement_Status = "Full Disagreement";
								statusResponse.AcceptedCVfromStatus();
								return statusResponse;
							}
						}
					}
				}
			}
		}
		

		return statusResponse;
	}

	/**
	 * 
	 * @param listoflines
	 * @return
	 */
	private String getPostedAccount(String[] listoflines) {

		String FinalPostedAccNo = "";

		for (String line : listoflines) {

			for (String key : companyKeywords) {

				if (line.toLowerCase().contains(key)) {
					Matcher match = Pattern.compile("(?<=\\[)[A-Z0-9]{5,20}(?=\\])").matcher(line);
					// System.out.println(line);
					try {
						while (match.find()) {
							FinalPostedAccNo = match.group();
							break;
						}
					} catch (Exception e) {
						// TODO: handle exception
						FinalPostedAccNo = "";
						// System.out.println("Exceptions at Account no
						// extraction");
					}
				}
			}

		}

		return FinalPostedAccNo;

	}

	private String getCurrency(String[] listoflines) {

		String FinalCurrency = "";

		Map<String, Integer> currencyRecord = new HashMap<String, Integer>();

		for (String line : listoflines) {

			if (line.toLowerCase().contains("reporting ccy") || line.toLowerCase().contains("reporting currency")) {

				for (String curr : currencyKeywords) {
					if (line.contains(curr)) {
						FinalCurrency = curr;
						return FinalCurrency;
					}
				}
			}

			String[] words = line.split(" ");

			for (String word : words) {

				if (currencyKeywords.contains(word)) {
					if (currencyRecord.containsKey(word)) {
						currencyRecord.put(word, currencyRecord.get(word) + 1);
					} else {
						currencyRecord.put(word, 1);
					}
				}

			}

		}

		try {
			FinalCurrency = Collections.max(currencyRecord.entrySet(), Map.Entry.comparingByValue()).getKey();
		} catch (Exception e) {
			// TODO: handle exception
			FinalCurrency = "";
		}
		return FinalCurrency;

	}

	private String flushKMdenominations(String amt) {

		try {
			amt = amt.toLowerCase().replace("k", ",000");
			amt = amt.toLowerCase().replace("m", ",000,000");
			return amt;

		} catch (Exception e) {
			// TODO: handle exception
			return amt;
		}

	}

	private String getAcceptedCallValue(String[] listoflines, String agreementSatus, String callValue) {

		String FinalAcceptedCallValue = "";

		if (agreementSatus.contains("Full")) {
			if (agreementSatus.contains("Disagreement")) {
				FinalAcceptedCallValue = "0.00";
			} else/* if(agreementSatus.contains("Agreement")) */ {
				FinalAcceptedCallValue = callValue;
			}
		}

		for (String line : listoflines) {

			if (line.contains("From:")) {
				break;
			}
			// System.out.println(line);
			for (String payWord : paymentKeywords) {

				if (line.toLowerCase().contains(" " + payWord + " ")
						|| line.toLowerCase().contains(" " + payWord + "s ")) {
					Matcher match = Pattern.compile("[.,0-9]+(k|K|m|M)?").matcher(line);
					try {
						while (match.find()) {
							FinalAcceptedCallValue = match.group();
							return flushKMdenominations(FinalAcceptedCallValue);
						}
					} catch (Exception e) {
						// TODO: handle exception
						FinalAcceptedCallValue = "";
					}
				}
			}

		}

		return flushKMdenominations(FinalAcceptedCallValue);

	}

	private String getCallValue(String[] listoflines) {

		String FinalCallValue = "";

		for (String line : listoflines) {

			if (line.toLowerCase().contains("movement from") || line.toLowerCase().contains("due to")) {
				Matcher match = Pattern.compile("[.,0-9]+(k|K|m|M)?").matcher(line);
				try {
					while (match.find()) {
						FinalCallValue = match.group();
						break;
					}
				} catch (Exception e) {
					// TODO: handle exception
					FinalCallValue = "";
				}
			}

		}

		return flushKMdenominations(FinalCallValue);

	}

	public List<Entry<String, String>> extractMarginRequirements(MarginCommunicationEntity marginComEntity) {

		List<Entry<String, String>> FinalExtractionResults = new ArrayList<Entry<String, String>>();

		String mailbody = preprocessBodyTagString(marginComEntity.mailBody);

		String[] listoflines = mailbody.split("\n");

		FinalExtractionResults.add(new SimpleEntry<String, String>("Reporting Currency", getCurrency(listoflines)));

		FinalExtractionResults.add(new SimpleEntry<String, String>("Posted A/C No.", getPostedAccount(listoflines)));

		FinalExtractionResults.add(new SimpleEntry<String, String>("Call Value", getCallValue(listoflines)));

		StatusResponse statusResponse = getAgreementStatusAndAcceptedCallValue(listoflines, FinalExtractionResults.get(2).getValue());
		
		FinalExtractionResults.add(new SimpleEntry<String, String>("Agreement Status", statusResponse.agreement_Status));
		
		FinalExtractionResults.add(new SimpleEntry<String, String>("Accepted Call Value", statusResponse.acceptedCallValue));
		
		FinalExtractionResults.add(new SimpleEntry<String, String>("Reference Id", getRefId(listoflines)));

		return FinalExtractionResults;

	}

}
