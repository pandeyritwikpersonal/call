package com.syne.innovation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;

import org.apache.poi.hsmf.MAPIMessage;
import org.apache.poi.hsmf.datatypes.AttachmentChunks;
import org.apache.poi.hsmf.exceptions.ChunkNotFoundException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MailParser {
	/**
	 * The stem used to create file names for the text file and the directory
	 * that contains the attachments.
	 */
	private String fileNameStem;

	/**
	 * The Outlook MSG file being processed.
	 */
	private MAPIMessage msg;

	public MailParser(String fileName) throws IOException {
			fileNameStem = fileName;
			if(fileNameStem.endsWith(".msg") || fileNameStem.endsWith(".MSG")) {
				fileNameStem = fileNameStem.substring(0, fileNameStem.length() - 4);
			}
			msg = new MAPIMessage(fileName);
		}

	/**
	 * Processes the message.
	 * 
	 * @throws IOException
	 *             if an exception occurs while writing the message out
	 * @throws ChunkNotFoundException 
	 */
	public void processMessage() throws IOException, ChunkNotFoundException {
		String txtFileName = fileNameStem + ".txt";
		String attDirName = fileNameStem + "-att";
		PrintWriter txtOut = null;
		try {
			
			txtOut = new PrintWriter(txtFileName);
			try {
				String displayFrom = msg.getDisplayFrom();
				txtOut.println("From: " + displayFrom);
			} catch (ChunkNotFoundException e) {
				// ignore
			}
			try {
				String displayTo = msg.getDisplayTo();
				txtOut.println("To: " + displayTo);
			} catch (ChunkNotFoundException e) {
				// ignore
			}
			try {
				String displayCC = msg.getDisplayCC();
				txtOut.println("CC: " + displayCC);
			} catch (ChunkNotFoundException e) {
				// ignore
			}
			try {
				String displayBCC = msg.getDisplayBCC();
				txtOut.println("BCC: " + displayBCC);
			} catch (ChunkNotFoundException e) {
				// ignore
			}
			try {
				String subject = msg.getSubject();
				txtOut.println("Subject: " + subject);
			} catch (ChunkNotFoundException e) {
				// ignore
			}
			try {
				String body = msg.getTextBody();
				System.out.println(msg.getHtmlBody());
				txtOut.println(body);
			} catch (ChunkNotFoundException e) {
				System.err.println("No message body");
			}

			AttachmentChunks[] attachments = msg.getAttachmentFiles();
			if (attachments.length > 0) {
				File d = new File(attDirName);
				if (d.mkdir()) {
					for (AttachmentChunks attachment : attachments) {
						processAttachment(attachment, d);
					}
				} else {
					System.err.println("Can't create directory " + attDirName);
				}
			}
		} finally {
			if (txtOut != null) {
				txtOut.close();
			}
		}
	}

	/**
	 * Processes a single attachment: reads it from the Outlook MSG file and
	 * writes it to disk as an individual file.
	 *
	 * @param attachment
	 *            the chunk group describing the attachment
	 * @param dir
	 *            the directory in which to write the attachment file
	 * @throws IOException
	 *             when any of the file operations fails
	 */
	public void processAttachment(AttachmentChunks attachment, File dir) throws IOException {
		String fileName = attachment.attachFileName.toString();
		if (attachment.attachLongFileName != null) {
			fileName = attachment.attachLongFileName.toString();
		}

		File f = new File(dir, fileName);
		OutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(f);
			fileOut.write(attachment.attachData.getValue());
		} finally {
			if (fileOut != null) {
				fileOut.close();
			}
		}
	}
	
	public void readExcel() throws IOException{
		String excelFilePath = "Books.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
         
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
         
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
             
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                 
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                }
                System.out.print(" - ");
            }
            System.out.println();
        }
         
        workbook.close();
        inputStream.close();
	}

	/**
	 * Processes the list of arguments as a list of names of Outlook MSG files.
	 * 
	 * @param args
	 *            the list of MSG files to process
	 * @throws ChunkNotFoundException 
	 */
	public static void main(String[] args) throws ChunkNotFoundException {
		if (args.length > 0) {
			System.err.println("No files names provided");
		} else {
			//for (int i = 0; i < args.length; i++) {
				try {
					MailParser processor = new MailParser("C:\\Users\\ritwik.pandey\\Desktop\\First.msg");
					processor.processMessage();
				} catch (IOException e) {
					System.err.println("Could not process : " + e);
				}
			//}
		}
	}

}