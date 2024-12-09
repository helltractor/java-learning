package com.helltractor.demo.editor;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class TextEditor {
	
	private StringBuilder buffer = new StringBuilder();
	
	public void copy() {
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable textTransferable = new StringSelection(buffer.toString());
		clip.setContents(textTransferable, null);
	}

	public void paste() {
		Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable clipTf = sysClip.getContents(null);
		if (clipTf != null) {
			if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				try {
					String text = (String) clipTf.getTransferData(DataFlavor.stringFlavor);
					add(text);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void add(String s) {
		buffer.append(s);
	}
	
	public void delete() {
		if (buffer.length() > 0) {
			buffer.deleteCharAt(buffer.length() - 1);
		}
	}

	public String getState() {
		return buffer.toString();
	}
	
	public void setState(String state) {
		buffer.delete(0, buffer.length());
		buffer.append(state);
	}
	
	public void print() {
		System.out.println(this.buffer.toString());
	    }
    
}