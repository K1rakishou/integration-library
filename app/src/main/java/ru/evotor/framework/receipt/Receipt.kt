package ru.evotor.framework.receipt

import java.math.BigDecimal

/**
 * Чек
 */
data class Receipt
(
        /**
         * Заголовок чека
         */
        val header: Header,
        /**
         * Печатные формы чека
         */
        val printDocuments: List<PrintReceipt>
) {

    /**
     * Список всех позиций чека
     */
    fun getPositions(): List<Position> {
        return printDocuments
                .flatMap { it.positions }
                .toList()
    }

    /**
     * Список всех оплат чека
     */
    fun getPayments(): List<Payment> {
        return printDocuments
                .map { it.payments }
                .flatMap { it.keys }
                .distinct()
    }

    /**
     * Заголовок чека
     */
    data class Header(
            /**
             * Uuid чека
             */
            val uuid: String,
            /**
             * Номер чека
             */
            val number: String
    )

    /**
     * Печатная форма чека
     */
    data class PrintReceipt(
            /**
             * Печатная группа
             */
            val printGroup: PrintGroup?,
            /**
             * Позиции
             */
            val positions: List<Position>,
            /**
             * Оплаты
             */
            val payments: Map<Payment, BigDecimal>,
            /**
             * Сдача
             */
            val changes: Map<Payment, BigDecimal>
    )
}