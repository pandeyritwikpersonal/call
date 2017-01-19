package margincall;


import java.util.List;
import java.io.File;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;

import opennlp.tools.namefind.*;
import opennlp.tools.tokenize.*;

import opennlp.tools.sentdetect.SentenceDetectorME;

import mailData.MarginCommunicationEntity;

import java.util.regex.*;  

public class MainClass {

	
//	static void runTokenisation(String sent){
//		
//		Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
//		
//		String[] tokens = tokenizer.tokenize(sent);
//		
//		for (String token : tokens){
//			System.out.println(token);
//		}
//		
//	}
	
	
	public static void main(String[] args){
		
////		String data = "I want my ice cream by Mr. Naunidh. he is a good boy. Mrs.title is going to market. ";
//
//		SentenceDetectorUtil sentenceDetectorutil = new SentenceDetectorUtil(
//				    "/Users/naunidhsingh/Documents/workspace/MarginCall/binFiles/en-sent.bin");
//		  
//		TokenizerUtil tokeniserutil = new TokenizerUtil(
//					"/Users/naunidhsingh/Documents/workspace/MarginCall/binFiles/en-token.bin");

//		
//		 
//		 
//		String[] sentences = sentenceDetectorutil.getSentences(data);
//		
//		for (String sentence : sentences){
//			
//			String[] tokens = tokeniserutil.getLearnableTokenizer().tokenize(sentence);
//			
//			for (String token : tokens){
//				
//				System.out.println(token);
//				
//			}
//			System.out.println(" --> ");
//			
//		}
		
		
		MarginCommunicationEntity marginComEntity = new MarginCommunicationEntity();
		
//		Please provide correct MTM. We will not pay a penny - disagreement
//		Agreed to pay USD 20000 - partially agreed
//		
		
		String strngResponse = "Disagree. MTM 20k.";
		
		String strngResponse_2 = "";
		
		marginComEntity.mailBody = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\"></head><body><style type=\"text/css\" style=\"display:none;\"><!-- P {margin-top:0;margin-bottom:0;} --></style><div id=\"divtagdefaultwrapper\" style=\"font-size:12pt;color:#000000;font-family:Calibri,Arial,Helvetica,sans-serif;\" dir=\"ltr\"><p></p><div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 16px; margin-top: 0px; margin-bottom: 0px;\"><font face=\"Calibri,Arial,Helvetica,sans-serif\" size=\"2\"><span style=\"font-size: 16px;\">" + strngResponse + "</span></font></div><div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 16px;\"><font face=\"Calibri,Arial,Helvetica,sans-serif\" size=\"2\"><span style=\"font-size: 16px;\"><br></span></font></div><div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 16px;\"><font face=\"Calibri,Arial,Helvetica,sans-serif\" size=\"2\"><span style=\"font-size: 16px;\">" + strngResponse_2 + "</span></font></div><div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 16px;\"><font face=\"Calibri,Arial,Helvetica,sans-serif\" size=\"2\"><span style=\"font-size: 16px;\"><br></span></font></div><div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 16px;\"><font face=\"Calibri,Arial,Helvetica,sans-serif\" size=\"2\"><span style=\"font-size: 16px;\">MTM 483,743</span></font></div><div><font face=\"Calibri,Arial,Helvetica,sans-serif\" size=\"2\"><span style=\"font-size: 16px;\"><br></span></font></div><br><p></p><p><br></p><div id=\"Signature\"><div id=\"divtagdefaultwrapper\" dir=\"ltr\" style=\"font-size:12pt; color:#000000; font-family:Calibri,Arial,Helvetica,sans-serif\"><p><span class=\"grey\" style=\"padding:0px; margin:0px; font-size:8pt; font-family:Arial,Helvetica,sans-serif; color:rgb(66,66,66)\"><b style=\"padding:0px; margin:0px\">Kind regards,</b>&nbsp;</span><br style=\"padding:0px; margin:0px; color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif; font-size:10.6667px\"><span class=\"grey\" style=\"padding:0px; margin:0px; font-size:8pt; font-family:Arial,Helvetica,sans-serif; color:rgb(66,66,66)\"><b style=\"padding:0px; margin:0px\">Akhand Pratap Singh</b></span><span style=\"color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif; font-size:10.6667px\"></span></p><p id=\"signatureInnerContent\" style=\"padding:0px; margin-right:0px; margin-left:0px; font-size:8pt; color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif\">Lead Technology - Synechron<br style=\"padding:0px; margin:0px\">Mobile &#43;91 8130 41 8363<br style=\"padding:0px; margin:0px\"></p><p id=\"signatureInnerContent\" style=\"padding:0px; margin-right:0px; margin-left:0px; font-size:8pt; color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif\"></p><p id=\"signatureInnerContent\" style=\"padding:0px; margin-right:0px; margin-left:0px; font-size:8pt; color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif\">Global Technology Park, Tower B- 1st Floor, Devarabeesanahalli, Marathahalli-Sarjapur Ring Road, Varthur Hobli, Bangalore-560103, India<br style=\"padding:0px; margin:0px\"></p><br style=\"padding:0px; margin:0px; color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif; font-size:10.6667px\"><br style=\"padding:0px; margin:0px; color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif; font-size:10.6667px\"><img alt=\"Synechron\" border=\"0\" height=\"26\" width=\"120\" style=\"padding: 0px; margin: 0px; border: 0px; vertical-align: middle; color: rgb(128, 128, 128); font-family: Arial, Helvetica, sans-serif; font-size: 10.6667px; user-select: none;\" src=\"http://www.synechron.com/sites/all/themes/synechron/images/logo.png\"><br style=\"padding:0px; margin:0px; color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif; font-size:10.6667px\"><a class=\"link\" href=\"http://www.synechron.com/\" style=\"padding:0px; margin:0px; color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif; font-size:7pt\" id=\"LPNoLP\">web</a><span style=\"color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif; font-size:10.6667px\">&nbsp;-&nbsp;</span><a class=\"link\" href=\"https://www.facebook.com/synechron\" style=\"padding:0px; margin:0px; color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif; font-size:7pt\" id=\"LPNoLP\">facebook</a><span style=\"color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif; font-size:10.6667px\">&nbsp;-&nbsp;</span><a class=\"link\" href=\"https://twitter.com/synechron\" style=\"padding:0px; margin:0px; color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif; font-size:7pt\" id=\"LPNoLP\">twitter</a><span style=\"color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif; font-size:10.6667px\">&nbsp;-&nbsp;</span><a class=\"link\" href=\"https://www.linkedin.com/company/synechron\" style=\"padding:0px; margin:0px; color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif; font-size:7pt\" id=\"LPNoLP\">linkedin</a><p></p><a class=\"link\" href=\"https://www.linkedin.com/company/synechron\" style=\"padding:0px; margin:0px; color:rgb(128,128,128); font-family:Arial,Helvetica,sans-serif; font-size:7pt\" id=\"LPNoLP\"></a><br><p></p></div></div></div><hr style=\"display:inline-block;width:98%\" tabindex=\"-1\"><div id=\"divRplyFwdMsg\" dir=\"ltr\"><font face=\"Calibri, sans-serif\" style=\"font-size:11pt\" color=\"#000000\"><b>From:</b> Margincall Admin<br><b>Sent:</b> Thursday, January 5, 2017 8:33:29 PM<br><b>To:</b> Akhand Singh<br><b>Subject:</b> [55d87d07-23e9-4647-9831-7f5808c30cb2] Margin Statement:INVESCO ASSET MANAGEMENT LTD - SEI GOBAL MASTER FUND PLC- THE SEI U.K. EQUITY FUND</font><div>&nbsp;</div></div><div><div id=\"x_divtagdefaultwrapper\" dir=\"ltr\" style=\"font-size:12pt; color:#000000; font-family:Calibri,Arial,Helvetica,sans-serif\"><p></p><p style=\"font-family:Times\">Morgan Stanley</p><p style=\"font-family:Times\">Entity: Morgan Stanley &amp;Co. International plc(MSIP)</p><p style=\"font-family:Times\">&nbsp;</p><p style=\"font-family:Times\">INVESCO ASSET MANAGEMENT LTD-SEI GLOBAL MASTER FUND PLC-THE SEI&nbsp; U.K&nbsp; EQUITY FUND[061783JK3]<br>&nbsp;&nbsp;<br>Attached please find a margin call on the transactions between you and Morgan Stanley. Please see the attached spreadsheet for trade detail information, Please contact Morgan Stanley's Coiiaterai Operations group to confirm margin caii and arrange settiement amount and value data.<br><br>ALL figures are from INVESCO ASSET MANAGEMENT LTD-SEI GLOBAL MASTER FUND PLC-THE SEI&nbsp; U.K&nbsp; EQUITY FUND perspective.<br></p><p style=\"font-family:Times\">Summary as at 01/05/2017 20:32:19</p><p style=\"font-family:Times\">Reporting Ccy&nbsp;:&nbsp;GBP<br><span style=\"font-size:12pt\"><b><br></b></span></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"><b><br></b></span></p><p></p><blockquote style=\"margin:0 0 0 40px; border:none; padding:0px\"><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\"><b>Product&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; MTM Rec/(Pay)</b></p><p></p><p></p></blockquote><blockquote style=\"margin:0 0 0 40px; border:none; padding:0px\"><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\">IED &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 3,171,203</p><p></p><p></p><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; -------------------------------------</p><p></p><p></p><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 3,171,203</p><p></p><p></p><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Upfront&nbsp; amount Rec/(PAY) &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; -</p><p></p><p></p><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\">Threshold &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 0</p><p></p><p></p><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;--------------------------------------</p><p></p><p></p><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Net Margin requirement &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;3,171,203</p><p></p><p></p></blockquote><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\"><br><b>&nbsp;</b><br></p><b></b><br><p></p><p></p><blockquote style=\"margin:0 0 0 40px; border:none; padding:0px\"><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\"><strong>Margin Summary</strong></p><p></p><p></p><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\"><br></p><p></p><p></p><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\">Net Margin required from [(due to) MS &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;3,171,203</p><p></p><p></p><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\">Customer Balances &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;(3,520,000)</p><p></p><p></p><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\">Min. Transfer Amount &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;250,000</p><p></p><p></p><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ---------------------------------------</p><p></p><p></p><p></p><p style=\"font-family:Times\"><span style=\"font-size:12pt\"></span></p><p style=\"font-family:Times\">Movement from /(due to) MS &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; (340,000)</p><p></p><p></p></blockquote><p></p><p style=\"font-family:Times\"><strong style=\"font-size:12pt\"><br></strong></p><p style=\"font-family:Times\"><strong style=\"font-size:12pt\"><br></strong></p><p style=\"font-family:Times\"><strong style=\"font-size:12pt\"><br></strong></p><p style=\"font-family:Times\"><strong style=\"font-size:12pt\">Notes</strong><br></p><p style=\"font-family:Times\">A positive MTM number indicates that the position is inane-money<br>A positive collateral balance indicates that MS is a receiver of collateral<br><br><strong>Contacts</strong><br>From: Morgan Stanley<br>Contact: LN COLL2<br>Email:&nbsp;&nbsp; Incoll2@morganstanley.com&nbsp;<br>Tel: &#43;44-207?677-4917<br>Fax: &#43;44-207-056-1753<br></p><br><p></p></div></div></body></html>";

		marginComEntity.subject ="[55d87d07-23e9-4647-9831-7f5808c30cb2] Margin Statement:INVESCO ASSET MANAGEMENT LTD - SEI GOBAL MASTER FUND PLC- THE SEI U.K. EQUITY FUND";
		
		MarginDataExtraction marginDataExt = new MarginDataExtraction();
		
		List<Entry<String, String>> FinalResults = marginDataExt.extractMarginRequirements(marginComEntity);
		
		for(Entry<String, String> dataExt : FinalResults){
			
			System.out.println(dataExt.getKey() + " :- " + dataExt.getValue());
			
		}
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
