package lightning.maps.db

import lightning.maps.utils.DbProperties
import com.mongodb.casbah.MongoCollection
import com.mongodb.casbah.MongoDB
import com.mongodb.casbah.MongoClient
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.ServerAddress
import com.mongodb.casbah.Imports.MongoCredential

class Mongo(host: String = DbProperties.DEFAULT_DATABASE_HOST, port: Int = DbProperties.DEFAULT_DATABASE_PORT, userName: String, password: String, dbName: String) {
  private val server: ServerAddress = new ServerAddress(host, port)
  private val credential: MongoCredential = MongoCredential(userName, dbName, password.toArray)
  private val client: MongoClient = MongoClient(server, List(credential))
  private val db: MongoDB = client(dbName)

  def collection(collectionName: String): MongoCollection = {
    db(collectionName)
  }

  def drop(collectionName: String): Unit = {
    if (exists(collectionName)) collection(collectionName).drop
  }

  def exists(collectionName: String): Boolean = {
    db.collectionExists(collectionName)
  }

  def insert(collectionName: String, vals: List[(String, Any)]): Unit = {
    insert(collection(collectionName), vals)
  }

  def insert(collectionName: String, value: (String, Any)): Unit = {
    insert(collection(collectionName), value)
  }

  def insert(collection: MongoCollection, vals: List[(String, Any)]): Unit = {
    collection += MongoDBObject(vals)
  }

  def insert(collection: MongoCollection, value: (String, Any)): Unit = {
    collection += MongoDBObject(value._1 -> value._2)
  }
}

object Mongo {
  private val client = new Mongo(userName=DbProperties.DEFAULT_DATABASE_USERNAME, password="password", dbName = DbProperties.DEFAULT_DATABASE_NAME)

  def insert(collectionName: String, vals: List[(String, Any)]): Unit = {
    client.insert(collectionName, vals)
  }

  def insert(collectionName: String, value: (String, String)): Unit = {
    client.insert(collectionName, value)
  }

  def drop(collectionName: String): Unit = {
    client.drop(collectionName)
  }
}