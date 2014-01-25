db.penaltyPostcode.find().forEach(function(penaltyPostcodeDoc) {
	var postcodeDistrictDoc = db.postcodeDistricts.findOne({"PCDistrict" : penaltyPostcodeDoc.District}, {"Latitude" : 1, "Longitude" : 1});
	if (postcodeDistrictDoc) {
		db.penaltyPostcode.update(
			{ "_id" : penaltyPostcodeDoc._id},
		 	{$set: { "Latitude" : postcodeDistrictDoc.Latitude, "Longitude" : postcodeDistrictDoc.Longitude }}
		);
	}
});
