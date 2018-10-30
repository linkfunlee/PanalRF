package com.netease.nim.demo.lesRf.rfapplayout;

import android.graphics.Bitmap;

public class Item {

	private String title;
	private Bitmap[] imgs;
	private boolean checked;
	private boolean visible;
	private String ID;
	private String SJBZ;
	private String JD,WD,RFNM;
	
	public String getJD()
	{
		return JD;
	}
	public void setJD(String JD)
	{
		this.JD = JD;
	}
	public void setWD(String WD)
	{
		this.WD = WD;
	}
	public String getWD()
	{
		return WD;
	}
	public String getTitle() {
		return title;
	}

	public String getID()
	{
		return ID;
	}
	public void setID(String ID)
	{
		this.ID = ID;
	}
	
	public String getSJBZ()
	{
		return SJBZ;
	}
	public void setSJBZ(String SJBZ)
	{
		this.SJBZ = SJBZ;
	}
	public String getRFNM()
	{
		return RFNM;
	}
	public void setRFNM(String RFNM)
	{
		this.RFNM = RFNM;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setCheck(boolean checked) {
		this.checked = checked;
		
	}
	public boolean getVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public Bitmap[] getImgs() {
		return imgs;
	}

	public void setImgs(Bitmap[] imgs) {
		this.imgs = imgs;
	}

	public Item() {
		super();
	}

	public Item(String title, Bitmap[] imgs) {
		super();
		this.title = title;
		this.imgs = imgs;
		this.RFNM = RFNM;
	}

}
