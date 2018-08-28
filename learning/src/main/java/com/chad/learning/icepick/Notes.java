package com.chad.learning.icepick;

public class Notes {
    /**
     * 在需要保存和恢复状态的变量前加上@state注解，
     * 在onSaveInstanceState方法加上Icepick.saveInstanceState(this, outState); 这一句保存状态，
     * 在需要恢复状态的地方加上Icepick.restoreInstanceState(this, savedInstanceState);
     */
}
