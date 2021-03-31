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
    var userInfo: CheckUserNumber?
    
    func checkExistingUser(status: Int, phone: String){
        AuthService.isUserNumberAvailable(status: status, phone: phone, completion: { [self] response in
            self.userAuth = response
            update(status: status, phone: phone)
        })
        
    }
    
    func update(status: Int, phone: String){
        self.userInfo?.status = status
        self.userInfo?.phone = phone

        print("🔴 \(status), \(phone)")
    }
    
    func signUpUser(name: String) {
        print("🔵 \(userInfo?.status), \(userInfo?.phone)")
        AuthService.signUpUser(status: userInfo?.status ?? 0, phone: userInfo?.phone ?? "0", name: name)
    }
}
