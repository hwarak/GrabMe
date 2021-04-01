//
//  CategoryViewController.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/01.
//

import UIKit

class CategoryViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    


    @IBAction func categoryButtonTapped(_ sender: UIButton) {
        /*
         restaurant: 0
         karaoke: 1
         hospital: 2
         cafe: 3
         gym: 4
         education: 5
         beauty: 6
         */
        
        print("🔴 tapped! \(sender.tag)")
        
        let vc = self.storyboard?.instantiateViewController(identifier: "CategoryList") as! CategoryListViewController
        vc.modalPresentationStyle = .fullScreen
        self.present(vc, animated: true, completion: nil)
    }
    
}
