//
//  CompleteRegistrationViewController.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/03/31.
//

import UIKit

class CompleteRegistrationViewController: UIViewController {

    //MARK: - Properties
    @IBOutlet weak var nameTF: UITextField!
    
    private var viewModel = RegistrationViewModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
//MARK: - Actions
    @IBAction func completeRegistrationButton(_ sender: Any) {
        viewModel.signUpUser(name: nameTF.text!)
        let vc = self.storyboard?.instantiateViewController(identifier: "completeRegistration") as! CompleteRegistrationViewController
        vc.modalPresentationStyle = .fullScreen
        present(vc, animated: true, completion: nil)
        
    }
    
}
