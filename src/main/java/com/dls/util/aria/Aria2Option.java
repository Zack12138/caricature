/**  
 * @Project Name:util  
 * @File Name:Aria2Option.java  
 * @Package Name:com.dls.util.aria  
 * @Date:2021年6月21日上午10:30:02  
 * Copyright (c) 2021, Zack All Rights Reserved.  
 *  
*/

package com.dls.util.aria;

public class Aria2Option {
	String dir;
	String out;
	String referer;

	public String getDir() {
		return dir;
	}

	public Aria2Option setDir(String dir) {
		this.dir = dir;
		return this;
	}

	public String getOut() {
		return out;
	}

	public Aria2Option setOut(String out) {
		this.out = out;
		return this;
	}

	public String getReferer() {
		return referer;
	}

	public Aria2Option setReferer(String referer) {
		this.referer = referer;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dir == null) ? 0 : dir.hashCode());
		result = prime * result + ((out == null) ? 0 : out.hashCode());
		result = prime * result + ((referer == null) ? 0 : referer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aria2Option other = (Aria2Option) obj;
		if (dir == null) {
			if (other.dir != null)
				return false;
		} else if (!dir.equals(other.dir))
			return false;
		if (out == null) {
			if (other.out != null)
				return false;
		} else if (!out.equals(other.out))
			return false;
		if (referer == null) {
			if (other.referer != null)
				return false;
		} else if (!referer.equals(other.referer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Aria2Option [dir=%s, out=%s, referer=%s]", dir, out, referer);
	}

	
	
}
