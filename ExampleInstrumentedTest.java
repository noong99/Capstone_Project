package com.example.bug_1128;

import static org.junit.Assert.assertEquals;

//import android.support.test.InstrumentationRegistry;
//import android.support.test.runner.AndroidJUnit4;

import android.app.Instrumentation;
import android.content.Context;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.bug_1128", appContext.getPackageName());
    }
}