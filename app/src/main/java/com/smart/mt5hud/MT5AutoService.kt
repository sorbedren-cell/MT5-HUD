package com.smart.mt5hud

import android.accessibilityservice.AccessibilityService
import android.os.Bundle
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class MT5AutoService : AccessibilityService() {
    var autoTradeEnabled = true
    val startingBalance = 37.99
    val maxDailyLoss = 7.00

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (!autoTradeEnabled) return
        val root = rootInActiveWindow ?: return

        // 1. قفل حجم العقد تلقائياً من 1.00 إلى 0.01 لحماية الحساب
        val lotNodes = root.findAccessibilityNodeInfosByText("1.00")
        for (node in lotNodes) {
            val args = Bundle()
            args.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "0.01")
            node.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, args)
        }
    }

    override fun onInterrupt() {}
}
