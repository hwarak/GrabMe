//
//  AuthService.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/03/29.
//

import UIKit

class AuthService {
    static func isUserNumberAvailable(status: Int, phone: String, completion: @escaping (CheckUserNumberResponse) -> Void){
        let user = CheckUserNumber.init(status: status, phone: phone)
  
        let config = URLSessionConfiguration.default
        let session = URLSession(configuration: config)
        
        guard let url: URL = URL(string:Config.ConfigUrl.checkUserStatus.url) else { return }
     //   guard let url: URL = URL(string:"http://192.168.20.21:8080/web/sign/check") else { return }
        var request = URLRequest(url: url)
        request.httpMethod = "POST"

        guard let encodeObject = AuthService.encode(data: user) else { return }
        request.httpBody = encodeObject
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        
        let dataTask: URLSessionDataTask = session.dataTask(with: request) { ( data, response, error) in
            guard error == nil else { return }
            guard let resultData = data else { return }

            guard let decodeObject = AuthService.decode(CheckUserNumberResponse.self, data: resultData) else { return }
            print("🔴 \(decodeObject.code), \(decodeObject.result)")
            completion(decodeObject)
        }
        dataTask.resume() 
    }
    
    static func signUpUser(status: Int, phone: String, name: String){
        let user = CheckUserNumber(status: status, phone: phone, name: name)
        print("---> \(user.name), \(status), \(phone)")
    }
    
    static func encode<T: Codable>(data: T) -> Data? {
        do{
            let request = try JSONEncoder().encode(data)
            //let resultString = String(data: request, encoding: .utf8)
            //print("Data in String: \(resultString)")
            return request
        } catch let error {
            print("EncodingError: \(error.localizedDescription)")
        }
        return nil
    }
    
    static func decode<T: Codable>(_ type: T.Type, data: Data) -> T? {
        do {
            let response = try JSONDecoder().decode(type, from: data)
            return response
        } catch let error {
            print("DecodingError: \(error.localizedDescription)")
        }
        return nil
    }
}

