/*
 *author:olunx
 *date:2009-10-10
 */

package com.olunx.option.mandict;

import com.olunx.R;
import com.olunx.util.Config;

import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.util.Log;
import android.view.inputmethod.EditorInfo;

public class TabDictSet extends PreferenceActivity {

	private PreferenceScreen root = null;
	private PreferenceScreen dictPathPref = null;// 词典路径
	private PreferenceScreen defaultDictPref = null;// 默认使用词典
	private EditTextPreference eachLesWCPref = null;// 每课单词数

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		root = getPreferenceManager().createPreferenceScreen(this);
		this.createPreScreen();
	}

	private void createPreScreen() {

		// 词典目录
		dictPathPref = getPreferenceManager().createPreferenceScreen(this);
		dictPathPref.setTitle(R.string.list_title_dict_dir);
		dictPathPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				Intent i = new Intent();
				i.setClass(TabDictSet.this, TabDictBrowser.class);
				startActivityForResult(i, 0);// 要获取返回值，必需用此方法
				return false;
			}
		});
		root.addPreference(dictPathPref);

		// 使用词典
		defaultDictPref = getPreferenceManager().createPreferenceScreen(this);
		defaultDictPref.setTitle(R.string.list_title_dict_current_use);
		defaultDictPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				Intent i = new Intent();
				i.setClass(TabDictSet.this, TabDictList.class);
				startActivityForResult(i, 0);// 要获取返回值，必需用此方法
				return false;
			}
		});
		root.addPreference(defaultDictPref);

		// 每课单词数
		eachLesWCPref = new EditTextPreference(this);
		eachLesWCPref.setKey("config_each_lesson_word_count");// 暂存配置，无其它关联。
		eachLesWCPref.setDialogTitle(R.string.dialog_title_each_lesson_word_count);
		eachLesWCPref.setDialogIcon(android.R.drawable.ic_dialog_info);
		eachLesWCPref.setTitle(R.string.list_title_each_lesson_word_count);
		eachLesWCPref.getEditText().setInputType(EditorInfo.TYPE_CLASS_NUMBER);
		eachLesWCPref.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				Log.i("editor", eachLesWCPref.getEditText().getText().toString());
				Config.getConfig().setEachLessonWordCount(TabDictSet.this, eachLesWCPref.getEditText().getText().toString());
				eachLesWCPref.setSummary(Config.getConfig().getEachLessonWordCountDes(TabDictSet.this));
				return false;
			}
		});
		root.addPreference(eachLesWCPref);

		// 其它设置
		PreferenceCategory otherSetPrefCat = new PreferenceCategory(this);
		otherSetPrefCat.setTitle("其它设置");
		root.addPreference(otherSetPrefCat);

		//真人发音
		final CheckBoxPreference speechPref = new CheckBoxPreference(this);
		
		// 发音功能
		final CheckBoxPreference ttsPref = new CheckBoxPreference(this);
		ttsPref.setKey("tts_function");
		ttsPref.setTitle("单词发音");
		ttsPref.setSummary("利用系统自带的文本发音功能。");
		ttsPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				if(ttsPref.isChecked()) {
					if(speechPref.isChecked()) {
						speechPref.setChecked(false);
					}
					//设置发音类型
					Config.getConfig().setSpeechType(TabDictSet.this, 1);
					Log.i("flag()", Config.getConfig().getSpeechType(TabDictSet.this));
				}
				return false;
			}
		});
		otherSetPrefCat.addPreference(ttsPref);

		
		//新的设置界面
        PreferenceScreen netScrPref = getPreferenceManager().createPreferenceScreen(this);
        netScrPref.setKey("screen_preference");
        netScrPref.setTitle("例句、真人发音");
        netScrPref.setSummary("这些都是需要连接到网络的功能。");
        otherSetPrefCat.addPreference(netScrPref);
        
		// 例句功能
		final CheckBoxPreference sentsPref = new CheckBoxPreference(this);
		sentsPref.setKey("sents_function");
		sentsPref.setTitle("例句功能");
		sentsPref.setSummary("开启此功能需要连接到网络。");
		sentsPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				boolean flag = sentsPref.isChecked();
				Config.getConfig().setCanConNetWord(TabDictSet.this, String.valueOf(flag));
				if(!flag) {
					Config.getConfig().setSpeechType(TabDictSet.this, 0);
				}
				Log.i("flag()", Config.getConfig().getCanConNetWord(TabDictSet.this));
				Log.i("flag()", Config.getConfig().getSpeechType(TabDictSet.this));
				return false;
			}
		});
		netScrPref.addPreference(sentsPref);

		// 真人发音
		speechPref.setKey("realpeople_function");
		speechPref.setTitle("真人发音");
		speechPref.setSummary("来自dict.cn的真人发音库数据。");
		speechPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				if(speechPref.isChecked()) {
					if(ttsPref.isChecked()) {
						ttsPref.setChecked(false);
					}
					//设置发音类型
					Config.getConfig().setSpeechType(TabDictSet.this, 2);
					Log.i("flag()", Config.getConfig().getSpeechType(TabDictSet.this));
				}
				return false;
			}
		});
		netScrPref.addPreference(speechPref);
		
		refreshPref();
		setPreferenceScreen(root);

		// 貌似是sdk的bug，要用代码定义依赖，就要这样做，在setPreferenceScreen方法后。
		getPreferenceManager().findPreference("realpeople_function").setDependency("sents_function");
	}

	//显示配置信息
	private void refreshPref() {
		Log.i("refresh", Config.getConfig().getDictDir(this));
		dictPathPref.setSummary(Config.getConfig().getDictDir(this));
		defaultDictPref.setSummary(Config.getConfig().getCurrentUseDictName(this));
		eachLesWCPref.setSummary(Config.getConfig().getEachLessonWordCountDes(this));
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case RESULT_OK: {
			this.refreshPref();
			break;
		}
		case RESULT_CANCELED:
			break;
		}
	}

}