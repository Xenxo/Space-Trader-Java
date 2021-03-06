/*******************************************************************************
 *
 * Space Trader for Windows 2.00
 *
 * Copyright (C) 2005 Jay French, All Rights Reserved
 *
 * Additional coding by David Pierron
 * Original coding by Pieter Spronck, Sam Anderson, Samuel Goldstein, Matt Lee
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * If you'd like a copy of the GNU General Public License, go to
 * http://www.gnu.org/copyleft/gpl.html.
 *
 * You can contact the author at spacetrader@frenchfryz.com
 *
 ******************************************************************************/
//using System;
//using System.Collections;
//using System.Drawing;
package spacetrader;

import jwinforms.Image;
import spacetrader.util.*;
import spacetrader.enums.*;
import spacetrader.guifacade.GuiEngine;

public class ShipSpec extends STSerializableObject
{
	private ShipType _type = ShipType.Custom;
	private Size _size = spacetrader.enums.Size.Tiny;
	private int _cargoBays = 0;
	private int _weaponSlots = 0;
	private int _shieldSlots = 0;
	private int _gadgetSlots = 0;
	private int _crewQuarters = 0;
	private int _fuelTanks = 0;
	private int _fuelCost = 0;
	private int _hullStrength = 0;
	private int _repairCost = 0;
	private int _price = 0;
	private int _occurrence = 0;
	private Activity _police = Activity.NA;
	private Activity _pirates = Activity.NA;
	private Activity _traders = Activity.NA;
	private TechLevel _minTech = TechLevel.Unavailable;
	private boolean _hullUpgraded = false;
	private int _imageIndex = Consts.ShipImgUseDefault;

	public ShipSpec()
	{}

	public ShipSpec(ShipType type, Size size, int cargoBays, int weaponSlots, int shieldSlots, int gadgetSlots,
			int crewQuarters, int fuelTanks, int fuelCost, int hullStrength, int repairCost, int price, int occurrence,
			Activity police, Activity pirates, Activity traders, TechLevel minTechLevel)
	{
		_type = type;
		_size = size;
		_cargoBays = cargoBays;
		_weaponSlots = weaponSlots;
		_shieldSlots = shieldSlots;
		_gadgetSlots = gadgetSlots;
		_crewQuarters = crewQuarters;
		_fuelTanks = fuelTanks;
		_fuelCost = fuelCost;
		_hullStrength = hullStrength;
		_repairCost = repairCost;
		_price = price;
		_occurrence = occurrence;
		_police = police;
		_pirates = pirates;
		_traders = traders;
		_minTech = minTechLevel;
	}

	public ShipSpec(Hashtable hash)
	{
		super(hash);
		_type = ShipType.FromInt( GetValueFromHash(hash, "_type", _type, Integer.class));
		_size = Size.FromInt( GetValueFromHash(hash, "_size", _size, Integer.class));
		_cargoBays = GetValueFromHash(hash, "_cargoBays", _cargoBays);
		_weaponSlots = GetValueFromHash(hash, "_weaponSlots", _weaponSlots);
		_shieldSlots = GetValueFromHash(hash, "_shieldSlots", _shieldSlots);
		_gadgetSlots = GetValueFromHash(hash, "_gadgetSlots", _gadgetSlots);
		_crewQuarters = GetValueFromHash(hash, "_crewQuarters", _crewQuarters);
		_fuelTanks = GetValueFromHash(hash, "_fuelTanks", _fuelTanks);
		_fuelCost = GetValueFromHash(hash, "_fuelCost", _fuelCost);
		_hullStrength = GetValueFromHash(hash, "_hullStrength", _hullStrength);
		_repairCost = GetValueFromHash(hash, "_repairCost", _repairCost);
		_price = GetValueFromHash(hash, "_price", _price);
		_occurrence = GetValueFromHash(hash, "_occurrence", _occurrence);
		_police = Activity.FromInt( GetValueFromHash(hash, "_police", _police, Integer.class));
		_pirates = Activity.FromInt( GetValueFromHash(hash, "_pirates", _pirates, Integer.class));
		_traders = Activity.FromInt( GetValueFromHash(hash, "_traders", _traders, Integer.class));
		_minTech = TechLevel.FromInt( GetValueFromHash(hash, "_minTech", _minTech, Integer.class));
		_hullUpgraded = GetValueFromHash(hash, "_hullUpgraded", _hullUpgraded);
		_imageIndex = GetValueFromHash(hash, "_imageIndex", Consts.ShipImgUseDefault);

		// Get the images if the ship uses the custom images.
		if (ImageIndex() == ShipType.Custom.CastToInt())
			GuiEngine.imageProvider.setCustomShipImages(GetValueFromHash(hash, "_images", GuiEngine.imageProvider
					.getCustomShipImages()));

		// Get the name if the ship is a custom design.
		if (Type() == ShipType.Custom)
		{
			Strings.ShipNames[ShipType.Custom.CastToInt()] = GetValueFromHash(hash, "_name",
					Strings.ShipNames[ShipType.Custom.CastToInt()]);

			Consts.ShipSpecs[ShipType.Custom.CastToInt()] = new ShipSpec(_type, _size, _cargoBays, _weaponSlots,
					_shieldSlots, _gadgetSlots, _crewQuarters, _fuelTanks, _fuelCost, _hullStrength, _repairCost,
					_price, _occurrence, _police, _pirates, _traders, _minTech);
			UpdateCustomImageOffsetConstants();
		}
	}

	@Override
	public Hashtable Serialize()
	{
		Hashtable hash = super.Serialize();

		hash.put("_type", _type.CastToInt());
		hash.put("_size", _size.CastToInt());
		hash.put("_cargoBays", _cargoBays);
		hash.put("_weaponSlots", _weaponSlots);
		hash.put("_shieldSlots", _shieldSlots);
		hash.add("_gadgetSlots", _gadgetSlots);
		hash.add("_crewQuarters", _crewQuarters);
		hash.add("_fuelTanks", _fuelTanks);
		hash.add("_fuelCost", _fuelCost);
		hash.add("_hullStrength", _hullStrength);
		hash.add("_repairCost", _repairCost);
		hash.add("_price", _price);
		hash.add("_occurrence", _occurrence);
		hash.add("_police", _police.CastToInt());
		hash.add("_pirates", _pirates.CastToInt());
		hash.add("_traders", _traders.CastToInt());
		hash.add("_minTech", _minTech.CastToInt());
		hash.add("_hullUpgraded", _hullUpgraded);

		// Only save image index if it's not the default.
		if (_imageIndex != Consts.ShipImgUseDefault)
			hash.add("_imageIndex", _imageIndex);

		// Save the name if the ship is a custom design.
		if (Type() == ShipType.Custom)
			hash.add("_name", Name());

		// Save the images if the ship uses the custom images.
		if (ImageIndex() == ShipType.Custom.CastToInt())
			hash.add("_images", GuiEngine.imageProvider.getCustomShipImages());

		return hash;
	}

	protected void SetValues(ShipType type)
	{
		int typeInt = type.CastToInt();

		_type = type;
		_size = Consts.ShipSpecs[typeInt]._size;
		_cargoBays = Consts.ShipSpecs[typeInt]._cargoBays;
		_weaponSlots = Consts.ShipSpecs[typeInt]._weaponSlots;
		_shieldSlots = Consts.ShipSpecs[typeInt]._shieldSlots;
		_gadgetSlots = Consts.ShipSpecs[typeInt]._gadgetSlots;
		_crewQuarters = Consts.ShipSpecs[typeInt]._crewQuarters;
		_fuelTanks = Consts.ShipSpecs[typeInt]._fuelTanks;
		_fuelCost = Consts.ShipSpecs[typeInt]._fuelCost;
		_hullStrength = Consts.ShipSpecs[typeInt]._hullStrength;
		_repairCost = Consts.ShipSpecs[typeInt]._repairCost;
		_price = Consts.ShipSpecs[typeInt]._price;
		_occurrence = Consts.ShipSpecs[typeInt]._occurrence;
		_police = Consts.ShipSpecs[typeInt]._police;
		_pirates = Consts.ShipSpecs[typeInt]._pirates;
		_traders = Consts.ShipSpecs[typeInt]._traders;
		_minTech = Consts.ShipSpecs[typeInt]._minTech;
		_hullUpgraded = Consts.ShipSpecs[typeInt]._hullUpgraded;
		_imageIndex = Consts.ShipSpecs[typeInt]._imageIndex;
	}

	public int Slots(EquipmentType type)
	{
		int count = 0;

		switch (type)
		{
		case Weapon:
			count = getWeaponSlots();
			break;
		case Shield:
			count = getShieldSlots();
			break;
		case Gadget:
			count = getGadgetSlots();
			break;
		}

		return count;
	}

	public void UpdateCustomImageOffsetConstants()
	{
		Image image = GuiEngine.imageProvider.getCustomShipImages()[0];
		int custIndex = ShipType.Custom.CastToInt();

		// Find the first column of pixels that has a non-white pixel for the X
		// value, and the last column for the width.
		int x = Functions.GetColumnOfFirstNonWhitePixel(image, 1);
		int width = Functions.GetColumnOfFirstNonWhitePixel(image, -1) - x + 1;
		Consts.ShipImageOffsets[custIndex].X = Math.max(2, x);
		Consts.ShipImageOffsets[custIndex].Width = Math.min(62 - Consts.ShipImageOffsets[custIndex].X, width);
	}

	public int CargoBays()
	{
		return _cargoBays;
	}

	public void CargoBays(int value)
	{
		_cargoBays = value;
	}

	public int FuelTanks()
	{
		return _fuelTanks;
	}

	public void FuelTanks(int value)
	{
		_fuelTanks = value;
	}

	public void setWeaponSlots(int weaponSlots)
	{
		_weaponSlots = weaponSlots;
	}

	public int getWeaponSlots()
	{
		return _weaponSlots;
	}

	public void setSize(Size size)
	{
		_size = size;
	}

	public Size getSize()
	{
		return _size;
	}

	public void setShieldSlots(int shieldSlots)
	{
		_shieldSlots = shieldSlots;
	}

	public int getShieldSlots()
	{
		return _shieldSlots;
	}

	public void setRepairCost(int repairCost)
	{
		_repairCost = repairCost;
	}

	public int getRepairCost()
	{
		return _repairCost;
	}

	public void setPrice(int price)
	{
		_price = price;
	}

	public int getPrice()
	{
		return _price;
	}

	public void setHullUpgraded(boolean hullUpgraded)
	{
		_hullUpgraded = hullUpgraded;
	}

	public boolean getHullUpgraded()
	{
		return _hullUpgraded;
	}

	public void setGadgetSlots(int gadgetSlots)
	{
		_gadgetSlots = gadgetSlots;
	}

	public int getGadgetSlots()
	{
		return _gadgetSlots;
	}

	public void setFuelCost(int fuelCost)
	{
		_fuelCost = fuelCost;
	}

	public int getFuelCost()
	{
		return _fuelCost;
	}

	public void setCrewQuarters(int crewQuarters)
	{
		_crewQuarters = crewQuarters;
	}

	public int getCrewQuarters()
	{
		return _crewQuarters;
	}

	public int HullStrength()
	{
		return _hullStrength + (getHullUpgraded() ? Consts.HullUpgrade : 0);
	}

	public void HullStrength(int value)
	{
		_hullStrength = value;
	}

	public Image Image()
	{
		return GuiEngine.imageProvider.getShipImages().getImages()[ImageIndex() * Consts.ImagesPerShip
				+ Consts.ShipImgOffsetNormal];
	}

	public Image ImageDamaged()
	{
		return GuiEngine.imageProvider.getShipImages().getImages()[ImageIndex() * Consts.ImagesPerShip
				+ Consts.ShipImgOffsetDamage];
	}

	public Image ImageDamagedWithShields()
	{
		return GuiEngine.imageProvider.getShipImages().getImages()[ImageIndex() * Consts.ImagesPerShip
				+ Consts.ShipImgOffsetSheildDamage];
	}

	public int ImageIndex()
	{
		return (_imageIndex == Consts.ShipImgUseDefault ? (int)Type().CastToInt() : _imageIndex);
	}

	public void ImageIndex(int value)
	{
		_imageIndex = (value == Type().CastToInt() ? Consts.ShipImgUseDefault : value);
	}

	public Image ImageWithShields()
	{
		return GuiEngine.imageProvider.getShipImages().getImages()[ImageIndex() * Consts.ImagesPerShip
				+ Consts.ShipImgOffsetShield];
	}

	public TechLevel MinimumTechLevel()
	{
		return _minTech;
	}

	public String Name()
	{
		return Strings.ShipNames[Type().CastToInt()];
	}

	public int Occurrence()
	{
		return _occurrence;
	}

	public Activity Police()
	{
		return _police;
	}

	public Activity Pirates()
	{
		return _pirates;
	}

	public Activity Traders()
	{
		return _traders;
	}

	public ShipType Type()
	{
		return _type;
	}

}
