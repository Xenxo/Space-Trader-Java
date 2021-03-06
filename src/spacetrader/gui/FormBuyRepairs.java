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
//using System.Drawing;
//using System.Collections;
//using System.ComponentModel;
//using System.Windows.Forms;
package spacetrader.gui;

import java.awt.Point;
import java.util.Arrays;

import javax.swing.UnsupportedLookAndFeelException;

import jwinforms.*;

import spacetrader.Commander;
import spacetrader.Game;

public class FormBuyRepairs extends jwinforms.WinformForm
{
	// #region Control Declarations

	private jwinforms.Button btnOk;
	private jwinforms.Label lblQuestion;
	private jwinforms.Button btnMax;
	private jwinforms.Button btnNothing;
	private jwinforms.NumericUpDown numAmount;

	// #endregion

	// #region Member Declarations

	private Game game = Game.CurrentGame();

	// #endregion

	// #region Methods

	public FormBuyRepairs()
	{
		InitializeComponent();

		Commander cmdr = game.Commander();
		numAmount.setMaximum(Math.min(cmdr.getCash(), (cmdr.getShip().HullStrength() - cmdr.getShip().getHull())
				* cmdr.getShip().getRepairCost()));
		numAmount.setValue(numAmount.getMaximum());
	}

	// #region Windows Form Designer generated code
	// / <summary>
	// / Required method for Designer support - do not modify
	// / the contents of this method with the code editor.
	// / </summary>
	private void InitializeComponent()
	{
		this.lblQuestion = new jwinforms.Label();
		this.numAmount = new jwinforms.NumericUpDown();
		this.btnOk = new jwinforms.Button();
		this.btnMax = new jwinforms.Button();
		this.btnNothing = new jwinforms.Button();
		((ISupportInitialize)(this.numAmount)).BeginInit();
		this.SuspendLayout();
		//
		// lblQuestion
		//
		this.lblQuestion.setAutoSize(true);
		this.lblQuestion.setLocation(new Point(8, 8));
		this.lblQuestion.setName("lblQuestion");
		this.lblQuestion.setSize(new Size(227, 13));
		this.lblQuestion.setTabIndex(3);
		this.lblQuestion.setText("How much do you want to spend on repairs?");
		//
		// numAmount
		//
		this.numAmount.setLocation(new Point(232, 6));
		this.numAmount.setMaximum(999);
		this.numAmount.setMinimum(1);
		this.numAmount.setName("numAmount");
		this.numAmount.setSize(new Size(44, 20));
		this.numAmount.setTabIndex(1);
		this.numAmount.setValue(888);
		//
		// btnOk
		//
		this.btnOk.setDialogResult(DialogResult.OK);
		this.btnOk.setFlatStyle(jwinforms.FlatStyle.Flat);
		this.btnOk.setLocation(new Point(69, 32));
		this.btnOk.setName("btnOk");
		this.btnOk.setSize(new Size(41, 22));
		this.btnOk.setTabIndex(2);
		this.btnOk.setText("Ok");
		//
		// btnMax
		//
		this.btnMax.setDialogResult(DialogResult.OK);
		this.btnMax.setFlatStyle(jwinforms.FlatStyle.Flat);
		this.btnMax.setLocation(new Point(117, 32));
		this.btnMax.setName("btnMax");
		this.btnMax.setSize(new Size(41, 22));
		this.btnMax.setTabIndex(3);
		this.btnMax.setText("Max");
		//
		// btnNothing
		//
		this.btnNothing.setDialogResult(DialogResult.Cancel);
		this.btnNothing.setFlatStyle(jwinforms.FlatStyle.Flat);
		this.btnNothing.setLocation(new Point(165, 32));
		this.btnNothing.setName("btnNothing");
		this.btnNothing.setSize(new Size(53, 22));
		this.btnNothing.setTabIndex(4);
		this.btnNothing.setText("Nothing");
		//
		// FormBuyRepairs
		//
		this.setAcceptButton(this.btnOk);
		this.setAutoScaleBaseSize(new Size(5, 13));
		this.setCancelButton(this.btnNothing);
		this.setClientSize(new Size(286, 63));
		this.setControlBox(false);
		this.Controls.addAll(Arrays.asList(new WinformControl[] { this.btnNothing, this.btnMax, this.btnOk,
				this.numAmount, this.lblQuestion }));
		this.setFormBorderStyle(jwinforms.FormBorderStyle.FixedDialog);
		this.setName("FormBuyRepairs");
		this.setShowInTaskbar(false);
		this.setStartPosition(FormStartPosition.CenterParent);
		this.setText("Hull Repair");
		((ISupportInitialize)(this.numAmount)).EndInit();
	}

	// #endregion

	// #endregion

	// #region Event Handlers
	// This action is not connected in the .NET version either.
	private void btnMax_Click(Object sender, EventArgs e)
	{
		numAmount.setValue(numAmount.getMaximum());
	}

	// #endregion

	// #region Properties

	public int Amount()
	{
		return numAmount.getValue();
	}

	// #endregion
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException
	{
		FormBuyRepairs form = new FormBuyRepairs();
		Launcher.runForm(form);
		System.out.println(form.Amount());
	}
}
