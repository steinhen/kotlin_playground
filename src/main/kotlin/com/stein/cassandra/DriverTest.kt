package com.stein.cassandra

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.Row
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

var sent = 0
fun main() {
    val t = measureTimeMillis {
        runBlocking {
            val channel = Channel<Pair<String, String>>(100)
            val session = CqlSession.builder().build()

            repeat(200) {
                launch {
                    for (entry in channel) {
//                        persist(entry, session)
                        write(entry, session)
                    }
                }
            }

            val produceTimes = 1
            repeat(produceTimes) {
                launch {
                    provideData().map {
                        println("sending $it")
                        sent++
                        channel.send(it)
                    }
                }
            }


            while (insertCount < 700 * produceTimes) {
                println("waiting app to finish")
                delay(100)
            }

            println("total saved = $insertCount")
            println("total sent = $sent")
            channel.close()
            session.close()
            println("closed")
        }
    }
    println("time = $t")
}

fun persist(entry: Pair<String, String>, session: CqlSession) {
    val (customer, mgb) = entry
    println("querying $customer and $mgb")
    val query =
        """
            SELECT country, customerid, mgbno, storeid, replacement, cc_entry_id, last_modified_importer, last_modified_source, neverreplace 
              FROM evaluate_replacements.customer_cache_replacements 
             WHERE country='DE' 
               AND customerid='$customer' 
               AND mgbno='$mgb'; 
        """.trimIndent()
//            AND storeid='00010' AND replacement='217413001001';
    session.executeAsync(query).thenAccept {
        val row: Row? = it.one()
        println(row?.getString("customerid")) // (3)
    } // (2)
}

var insertCount = 0
fun write(entry: Pair<String, String>, session: CqlSession) {
    val (customer, mgb) = entry
    println("inserting $customer and $mgb")

    val insert =
        """
        INSERT INTO evaluate_replacements.customer_cache_replacements 
        (country, customerid, mgbno, storeid, replacement, cc_entry_id, last_modified_importer, last_modified_source, neverreplace) 
        VALUES('DE', '$customer', '$mgb', '00010', '${insertCount++}', 'betty_direct_customer_article_1-6D4SQFC', '2021-12-22 13:27:38.106', '{"betty_direct_customer_article":"2020-10-02T05:33:33.727"}', false);
    """.trimIndent()

    session.executeAsync(insert).thenAccept { println("saved $customer") }
//    session.execute(insert)
//    println("saved $customer")
}

