//
//  CategoryListTableViewCell.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/05.
//

import UIKit

class CategoryListTableViewCell: UITableViewCell {

    @IBOutlet weak var restaurantImage: UIImageView!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        restaurantImage.roundCorners(cornerRadius: 10, maskedCorners: [.layerMinXMinYCorner, .layerMinXMaxYCorner])
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

    }

}


