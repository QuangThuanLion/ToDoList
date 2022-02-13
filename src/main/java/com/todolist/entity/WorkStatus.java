package com.todolist.entity;

/**
 * @author Admin
 *
 */
public enum WorkStatus {

	PLANNING{
		@Override
		public String defaultDescription() {
			return "work is planning";
		}
	},
	
	DOING {
		@Override
		public String defaultDescription() {
			return "work is doing";
		}
	},
	
	COMPLETE{
		@Override
		public String defaultDescription() {
			return "work is complete";
		}
	};
	
	public abstract String defaultDescription();
}
