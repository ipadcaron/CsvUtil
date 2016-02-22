package jp.caron.test;

public class TestDto {

	private int intVal;
	private float fltVal;
	private double dblVal;
	public int getIntVal() {
		return intVal;
	}
	public void setIntVal(int intVal) {
		this.intVal = intVal;
	}
	public float getFltVal() {
		return fltVal;
	}
	public void setFltVal(float fltVal) {
		this.fltVal = fltVal;
	}
	public double getDblVal() {
		return dblVal;
	}
	public void setDblVal(double dblVal) {
		this.dblVal = dblVal;
	}
	public TestDto(int intVal, float fltVal, double dblVal) {
		super();
		this.intVal = intVal;
		this.fltVal = fltVal;
		this.dblVal = dblVal;
	}
	@Override
	public String toString() {
		return "TestDto [intVal=" + intVal + ", fltVal=" + fltVal + ", dblVal="
				+ dblVal + "]";
	}
	public TestDto() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}


}
