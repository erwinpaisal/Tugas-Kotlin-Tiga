package com.erwin.tugaskotlin3

import org.w3c.dom.Text

class DataPostingan {
    var postID:String?=null
    var postText:String?=null
    var postImageURL:String?=null
    var postPersonUID:String?=null

    constructor(postIDs:String,postTexts: String, postImageURL:String, postPersonUIDs:String) {
        this.postID= postIDs
        this.postText= postTexts
        this.postImageURL=postImageURL
        this.postPersonUID=postPersonUIDs
    }

}