package com.scxh.android1503.ui.searchdialog;

import android.content.SearchRecentSuggestionsProvider;

public class MySearchSuggestionProvider extends SearchRecentSuggestionsProvider {
	public final static String AUTHORITY = "com.scxh.android1503.ui.searchdialog.MySearchSuggestionProvider";
	public final static int MODE = DATABASE_MODE_QUERIES;

	public MySearchSuggestionProvider() {
		super();
		setupSuggestions(AUTHORITY, MODE);
	}

}
