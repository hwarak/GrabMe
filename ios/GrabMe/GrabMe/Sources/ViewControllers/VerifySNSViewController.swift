//
//  VerifySNSViewController.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/03/31.
//

import UIKit

class VerifySNSViewController: UIViewController {
    //MARK: - Outlet
    @IBOutlet weak var verifySNSCode: UITextField!
    
    //MARK: - Properties
    private var viewmodel = RegistrationViewModel()
    
    //MARK: - LifeCycle
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    @IBAction func verifyCodeTapped(_ sender: Any) {
        let status = viewmodel.checkVerification(code: verifySNSCode.text!)
        
        if status {
            let vc = self.storyboard?.instantiateViewController(identifier: "completeRegistration") as! CompleteRegistrationViewController
            vc.modalPresentationStyle = .fullScreen
           // present(vc, animated: true, completion: nil)
            self.navigationController?.pushViewController(vc, animated: true)
        }
        else {
            //코드에러! 
        }
    }
    
}
