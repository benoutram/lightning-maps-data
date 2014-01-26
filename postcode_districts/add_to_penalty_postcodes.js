db.penaltyPostcode.find().forEach(function(penaltyPostcodeDoc) {
	var district = penaltyPostcodeDoc.District;
	//remove any leading zeros from the district
	district = district.replace(/^([a-z]+)(0)(.+)/i,"$1" + "$3");

	var postcodeDistrictDoc = db.postcodeDistricts.findOne({"PCDistrict" : district}, {"Latitude" : 1, "Longitude" : 1});
	if (postcodeDistrictDoc) {
		db.penaltyPostcode.update(
			{ "_id" : penaltyPostcodeDoc._id},
		 	{$set: { "Latitude" : postcodeDistrictDoc.Latitude, "Longitude" : postcodeDistrictDoc.Longitude }}
		);
	}
});
