db.postcodeDistricts.update({},
	{
		$unset: {
			Sectors: "",
			Units: "",
			Town: "",
			District: "",
			County: "",
			Region: "",
			Country: "",
			Eastings: "",
			Northings: "",
			GridRef: ""
		}
	},
	{ multi: true }
)
