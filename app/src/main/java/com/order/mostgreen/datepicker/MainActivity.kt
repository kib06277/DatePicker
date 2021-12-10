package com.order.mostgreen.datepicker

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.borax12.materialdaterangepicker.date.DatePickerDialog
import java.util.*

class MainActivity : AppCompatActivity() , DatePickerDialog.OnDateSetListener {

    //UI/UX 關聯
    lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = this.findViewById(R.id.button)
        button.setOnClickListener {
            // 顯示時間選擇棄談窗
            showDatePickerDialog()
        }
    }

    //顯示日期選擇器
    private fun showDatePickerDialog() {
        val now = Calendar.getInstance()
        val dpd = DatePickerDialog.newInstance(
            this@MainActivity,
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        )
        dpd.show(fragmentManager, "Datepickerdialog")
    }

    /**
     * @param yearn年(始)例：2018
     * @param monthOfYear月（始）例：8
     * @param dayOfMonth日（始）例：15
     * @param yearEnd（结束）例：2018
     * @param monthOfYearEnd（结束）例：8
     * @param dayOfMonthEnd（结束）例：16
     * 由於發現 此控件有個 bug，顯示的月份少一個月，所以在取數據時候會加 1
     */
    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int, yearEnd: Int, monthOfYearEnd: Int, dayOfMonthEnd: Int) {

        // 開始時間的時間戳
        val timeStart = DateUtils.getTimeStart("" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日")

        // 結束時間的時間戳
        val timeEnd = DateUtils.getTimeEnd("" + yearEnd + "年" + (monthOfYearEnd + 1) + "月" + dayOfMonthEnd + "日")

        Log.d("QQ", "【" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日" + "】——到——【" + yearEnd + "年" + (monthOfYearEnd + 1) + "月" + dayOfMonthEnd + "日】")
        Log.d("QQ", "【開始時間戳：" + timeStart + "】——到——【" + "結束時間戳：" + timeEnd + "】")

        //如果需要一些判斷操作，可以使用 try-catch 或者 is-else 判斷
        try {
            if (timeStart?.toLong()!! > timeEnd?.toLong()!!) {
                Toast.makeText(this, "結束時間不能小於開始時間", Toast.LENGTH_SHORT).show()
                return
            }
        } catch (e: Exception) {
            Toast.makeText(this, "選擇時間有誤", Toast.LENGTH_SHORT).show()
        }
    }

}