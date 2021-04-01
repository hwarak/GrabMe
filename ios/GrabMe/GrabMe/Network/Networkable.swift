//
//  Networkable.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/01.
//

import UIKit

protocol Networkable {
    static func reqeustAPI(request: URLRequest, completion: @escaping (Data) -> Void)
    
    static func decode<T: Codable>(_ type: T.Type, data: Data) -> T?
    static func encode<T: Codable>(data: T) -> Data?
}

extension Networkable {
    static func reqeustAPI(request: URLRequest, completion: @escaping (Data) -> Void) {
        
        URLSession(configuration: .default).dataTask(with: request) { data, response, error in
            guard error == nil else { return }
            
            guard let statusCode = (response as? HTTPURLResponse)?.statusCode else { return }
            let successRange = 200..<300 // 에러코드 확인
            guard successRange.contains(statusCode) else { return }
            guard let resultData = data else { return }
  
            completion(resultData)
        }.resume()
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

