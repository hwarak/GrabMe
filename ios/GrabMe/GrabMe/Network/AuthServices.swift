//
//  AuthServices.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/01.
//

import Foundation

class AuthServices: Networkable {
    static func isUserNumberAvailable(user: CheckUserNumber, completion: @escaping (Response) -> Void) {
        
        guard let encodeObject = self.encode(data: user) else { return }

        reqeustAPI(request: Networking.requestObject(type: .checkUserStatus, data: encodeObject, requestType: .post), completion: { data in

            let resultString = String(data: data
                                      , encoding: .utf8)
            
            //print("🔵 Data in String: \(resultString)")
            guard let decodeData = self.decode(Response.self, data: data) else { return }
            print("🔴 \(decodeData.statusCode), \(decodeData.data.code)")
            completion(decodeData)
        })
    }
    
    static func signUpUser(user: CheckUserNumber) {
        guard let encodeObject = self.encode(data: user) else { return }
        
        reqeustAPI(request: Networking.requestObject(type: .signUp, data: encodeObject, requestType: .post), completion: { data in
            //guard let decodeData = self.decode(CheckUserNumberResponse.self, data: data) else { return }
            //let resultString = String(data: data, encoding: .utf8)
            //print("Data in String: \(resultString)")
            
            guard let decodeData = self.decode(Response.self, data: data) else { return }
            print("🔴 \(decodeData.statusCode), \(decodeData.data.code)")
        })
    }
}
