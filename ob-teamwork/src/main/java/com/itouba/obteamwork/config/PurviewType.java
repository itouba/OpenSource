package com.itouba.obteamwork.config;

public enum PurviewType {
		add,
		mdf,
		del,
		query;
		/**
		 * 重载toString();
		 */
		public String toString(){
			switch (this){
			case add:
				return "add";
			case mdf:
				return "mdf";
			case del:
				return "del";
			case query:
				return "query";
			default :
				return "query";
			}
		}

		/**
		 * 重载valueOf();
		 */
		public String valueOf(){
			switch (this){
			case add:
				return "add";
			case mdf:
				return "mdf";
			case del:
				return "del";
			case query:
				return "quey";
			default :
				return "quey";
			}
		}

	public static void main(String[] args) {
		System.out.println(PurviewType.add.valueOf());
	}
}
