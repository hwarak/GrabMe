//
//  AuthService.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/03/29.
//

import UIKit

class AuthService {
    static func isUserNumberAvailable(status: Int, phone: String){
        let user = CheckUserNumber.init(status: status, phone: phone)
        print("\(user.phone), \(user.status)")
        
        
        let config = URLSessionConfiguration.default
        let session = URLSession(configuration: config)
        
        guard let url: URL = URL(string: "http://15.164.72.21:8080/grabMe/sign/check") else { return }
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        
        let jsonEncode = JSONEncoder()
        
        do {
            let data: Data = try jsonEncode.encode(user)
        
           // let resultString = String(data: data, encoding: .utf8)
            request.httpBody = data
            request.addValue("application/json", forHTTPHeaderField: "Content-Type")
            

        } catch (let error) {
            print(error.localizedDescription)
        }
        let dataTask: URLSessionDataTask = session.dataTask(with: request) { ( data, response, error) in
            guard error == nil else { return }
            guard let resultData = data else { return }
            do {
                let jsonDecoder: JSONDecoder = JSONDecoder()
                let responses: CheckUserNumberResponse = try jsonDecoder.decode(CheckUserNumberResponse.self, from: resultData)
                print("result data--> \(resultData)")
                let resultString = String(data: resultData, encoding: .utf8)
                print("resultString--> \(resultString)")

            } catch (let error) {
                print(error.localizedDescription)
            }
        }
        dataTask.resume()

        
    }
}
