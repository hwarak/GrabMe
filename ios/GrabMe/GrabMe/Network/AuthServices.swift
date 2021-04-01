//
//  AuthServices.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/01.
//

import Foundation

class AuthServices: Networkable {
    static func isUserNumberAvailable(user: CheckUserNumber, completion: @escaping (CheckUserNumberResponse) -> Void) {
        
        guard let encodeObject = self.encode(data: user) else { return }

        reqeustAPI(request: Networking.requestObject(type: .checkUserStatus, data: encodeObject), completion: { data in
            guard let decodeData = self.decode(CheckUserNumberResponse.self, data: data) else { return }
            print("🔴 \(decodeData.code), \(decodeData.result)")
            completion(decodeData)
        })
    }
    
    static func signUpUser(user: CheckUserNumber) {
        guard let encodeObject = self.encode(data: user) else { return }
        
        reqeustAPI(request: Networking.requestObject(type: .signUp, data: encodeObject), completion: { data in
            //guard let decodeData = self.decode(CheckUserNumberResponse.self, data: data) else { return }
            let resultString = String(data: data
                                      , encoding: .utf8)
            
            print("Data in String: \(resultString)")
        })
    }
}
