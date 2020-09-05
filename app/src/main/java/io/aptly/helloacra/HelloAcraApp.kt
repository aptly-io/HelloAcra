package io.aptly.helloacra

import android.app.Application
import android.widget.Toast
import org.acra.ACRA
import org.acra.config.*
import org.acra.data.StringFormat
import org.acra.sender.HttpSender

class HelloAcraApp: Application() {

    override fun onCreate() {
        super.onCreate()
        setupAcra()
    }

    private fun setupAcra() {
        val builder = CoreConfigurationBuilder(this)
            .setBuildConfigClass(BuildConfig::class.java)
            .setReportFormat(StringFormat.JSON)
            .setDeleteUnapprovedReportsOnApplicationStart(true)
            .setEnabled(true)

        builder.getPluginConfigurationBuilder(ToastConfigurationBuilder::class.java)
            .setText("A painful end for HelloAcra")
            .setLength(Toast.LENGTH_LONG )
            .setEnabled(true)

        builder.getPluginConfigurationBuilder(HttpSenderConfigurationBuilder::class.java)
            .setCompress(false)
                // TODO add Acrarium's host name
            .setUri("https://${YOUR_ACRA_HOST_HERE}/report")
            .setHttpMethod(HttpSender.Method.POST)
                // TODO add Acrarium's app credentials
            .setBasicAuthLogin("")
            .setBasicAuthPassword("")
            .setDropReportsOnTimeout(false)
            .setConnectionTimeout(10_000)
            .setSocketTimeout(10_000)
            .setEnabled(true)

//        builder.getPluginConfigurationBuilder(MailSenderConfigurationBuilder::class.java)
//            .setMailTo("francis.meyvis@gmail.com")
//            .setSubject("HelloAcra crash report")
//            .setReportAsFile(true)
//            .setReportFileName("crash_report.json")
//            .setBody("See the attachement")
//            .setEnabled(true)

//        builder.getPluginConfigurationBuilder(DialogConfigurationBuilder::class.java)
//            .setTitle("Title")
//            .setText("Text")
//            .setPositiveButtonText("PositiveButtonText")
//            .setNegativeButtonText("NegativeButtonText")
//            .setCommentPrompt("CommentPrompt")
//            .setEmailPrompt("EmailPrompt")
//            .setEnabled(true)

//        builder.getPluginConfigurationBuilder(NotificationConfigurationBuilder::class.java)
//            .setChannelDescription("ChannelDescription")
//            .setChannelName("ChannelName")
//            .setCommentPrompt("CommentPrompt")
//            .setDiscardButtonText("Discard")
//            .setSendButtonText("SendButtonText")
//            .setSendOnClick(true)
//            .setSendWithCommentButtonText("SendWithCommentButtonText")
//            .setText("Text")
//            .setTickerText("TickerText")
//            .setTitle("Title")
//            .setEnabled(true)

//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
//            builder.getPluginConfigurationBuilder(SchedulerConfigurationBuilder::class.java)
//                .setRequiresNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//                .setRequiresBatteryNotLow(true)
//                .setRequiresDeviceIdle(true)
//                .setRestartAfterCrash(false)
//                .setEnabled(true)
//        }

        builder.getPluginConfigurationBuilder(LimiterConfigurationBuilder::class.java)
            .setDeleteReportsOnAppUpdate(true)
//            .setExceptionClassLimit(2) // limit for reports with the same exception class
            .setFailedReportLimit(2) // limit for unsent reports
//            .setStacktraceLimit(2) // limit for reports with the same stacktrace
//            .setIgnoredCrashToast(String ignoredCrashToast) // toast shown when a report was not collected or sent because a limit was exceeded
//            .setOverallLimit(int overallLimit) //general limit of reports
//            .setPeriod(long period) // Reports which have been collected before this will not be considered for any limits except AcraLimiter.failedReportLimit()
//            .setPeriodUnit(TimeUnit periodUnit) // Unit of AcraLimiter.period()
//            .setResetLimitsOnAppUpdate(boolean resetLimitsOnAppUpdate) // Resetting limits after an app update allows you to see if a bug still exists.
            .setEnabled(false)
        ACRA.init(this, builder)
    }
}