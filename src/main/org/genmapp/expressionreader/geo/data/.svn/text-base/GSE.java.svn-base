/*
 Copyright 2010 Alexander Pico
 Licensed under the Apache License, Version 2.0 (the "License"); 
 you may not use this file except in compliance with the License. 
 You may obtain a copy of the License at 
 	
 	http://www.apache.org/licenses/LICENSE-2.0 
 	
 Unless required by applicable law or agreed to in writing, software 
 distributed under the License is distributed on an "AS IS" BASIS, 
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 See the License for the specific language governing permissions and 
 limitations under the License. 
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.geo.data;

import java.util.List;

/**
 *
 * @author djiao
 */
public class GSE extends SOFT {
    private List<SOFT> platforms; 
    private List<SOFT> samples;
    private SOFT database;

    public GSE(SOFT soft) {
        super();
        this.setFields(soft.getFields());
        this.setType(Type.GSE);
        this.setId(soft.getId());
        this.setDataTables(soft.getDataTables());
    }

    public SOFT getDatabase() {
        return database;
    }

    public void setDatabase(SOFT database) {
        this.database = database;
    }

    public List<SOFT> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<SOFT> platforms) {
        this.platforms = platforms;
    }

    public List<SOFT> getSamples() {
        return samples;
    }

    public void setSamples(List<SOFT> samples) {
        this.samples = samples;
    }

    /**
     * Returns the type
     * @return
     */
    @Override
    public Type getType() {
        return Type.GSE;
    }


}
