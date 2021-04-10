//
//  CategoryListTableViewCell.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/05.
//

import UIKit
import Kingfisher

class CategoryListTableViewCell: UITableViewCell {

    @IBOutlet weak var placeImage: UIImageView!
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var address: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        placeImage.roundCorners(cornerRadius: 10, maskedCorners: [.layerMinXMinYCorner, .layerMinXMaxYCorner])
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

    }
    
    func configureUI(_ item: CategoryList){
        title.text = item.title
        address.text = item.address
        let url = URL(string: item.thumbnail!)
        placeImage.kf.setImage(with: url)
        
        
    }

}


