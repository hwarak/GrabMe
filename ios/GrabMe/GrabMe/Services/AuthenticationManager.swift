//
//  AuthenticationManager.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/03/31.
//

import UIKit

class AuthenticationManager {
    
    static let shared = AuthenticationManager()
    var userAuth: CheckUserNumberResponse?

    var status: Int?
    var phone: String?
    
    func checkExistingUser(status: Int, phone: String){
        let user = CheckUserNumber.init(status: status, phone: phone)
        AuthServices.isUserNumberAvailable(user: user, completion: { [weak self] response in
            self?.userAuth = response
            self?.update(status: status, phone: phone)
        })
    
    }
    
    func update(status: Int, phone: String){
        self.status = status
        self.phone = phone
    }
    
    func signUpUser(name: String) {
        let user = CheckUserNumber(status: self.status!, phone: self.phone!, name: name)
        AuthServices.signUpUser(user: user)
    }
}
