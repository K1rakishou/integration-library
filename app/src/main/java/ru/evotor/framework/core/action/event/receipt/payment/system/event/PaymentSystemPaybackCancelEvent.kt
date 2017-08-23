package ru.evotor.framework.core.action.event.receipt.payment.system.event

import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.BundleUtils
import java.math.BigDecimal

class PaymentSystemPaybackCancelEvent(
        val receiptUuid: String,
        val sum: BigDecimal,
        val rrn: String,
        val description: String
) : PaymentSystemEvent(OperationType.PAYBACK_CANCEL) {

    override fun toBundle(): Bundle {
        val result = super.toBundle()
        result.putString(KEY_RECEIPT_UUID, receiptUuid)
        result.putString(KEY_SUM, sum.toPlainString())
        result.putString(KEY_RRN, rrn)
        result.putString(KEY_DESCRIPTION, description)
        return result
    }

    companion object {
        private val KEY_RECEIPT_UUID = "receiptUuid"
        private val KEY_DESCRIPTION = "description"
        private val KEY_SUM = "sum"
        private val KEY_RRN = "rrn"

        fun create(bundle: Bundle?): PaymentSystemPaybackCancelEvent? {
            if (bundle == null) {
                return null
            }
            val receiptUuid = bundle.getString(KEY_RECEIPT_UUID, null)
            val sum = BundleUtils.getMoney(bundle, KEY_SUM)
            val rrn = bundle.getString(KEY_RRN, null)
            val description = bundle.getString(KEY_DESCRIPTION, null)
            return PaymentSystemPaybackCancelEvent(receiptUuid, sum!!, rrn, description)
        }
    }
}