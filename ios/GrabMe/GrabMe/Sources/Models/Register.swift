//
//  Register.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/03/29.
//

import UIKit

struct CheckUserNumber: Codable {
    var status: Int
    var phone: String
    
    mutating func update(status: Int, phone: String){
        self.status = status
        self.phone = phone
    }
}

struct CheckUserNumberResponse: Codable{
    var result: String
    var code: String
}


