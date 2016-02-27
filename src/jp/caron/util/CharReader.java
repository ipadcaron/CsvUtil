package jp.caron.util;

class CharReader {

	char[] datas;
	int pos, max;

	public CharReader() {
		this.datas = null;
		this.pos = 0;
		this.max = 0;
	}

	public CharReader(String data) {
		reset(data);
	}

	public void reset(String data) {
		this.datas = data.toCharArray();
		this.pos = 0;
		this.max = this.datas.length;
	}

	public boolean hasNext() {
		return this.pos < this.max;
	}
	public char next() {
		return this.datas[this.pos++];
	}
	/**
	 * １文字戻る
	 */
	public void back() {
		if (this.pos > 0)
			this.pos--;
	}
	/**
	 * 空白が続く限り読み飛ばす
	 */
	public void skip() {
		while (this.pos < this.max) {
			if (this.datas[this.pos] == ' ') this.pos++;
			else break;
		}
	}
	/**
	 *指定文字を検出するまで読み飛ばす
	 * @param ch
	 */
	public void skip(char ch) {
		while (this.pos < this.max) {
			if (this.datas[this.pos] != ch) this.pos++;
			else break;
		}
	}
}
