/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.bigdata.car.recommender.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hiep
 */
public class Make extends BaseModel {
	private final List<Model> models = new ArrayList<>();

	public Make() {
	}

	public Make(long id, String name) {
		super(id, name);
	}

	public void addModel(Model model) {
		this.models.add(model);
	}

	public Model getModel(String modelName) {
		Model model = null;
		for (Model _model : models) {
			if (_model.getName().equals(modelName))
				model = _model;
		}
		return model;
	}
}
