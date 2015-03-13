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

package cytoscape.cythesaurus;

import java.util.Map;
import java.util.Set;

public abstract interface CyThesaurusServiceClient
{
  public abstract boolean isServiceAvailable();

  public abstract double serviceVersion();

  public abstract boolean openAttributeConfigDialog();

  public abstract boolean openMappingResourceConfigDialog();

  public abstract Set<String> allIDMappers();

  public abstract Set<String> selectedIDMappers();

  public abstract boolean registerIDMapper(String paramString1, String paramString2, String paramString3);

  public abstract boolean unregisterIDMapper(String paramString);

  public abstract boolean setIDMapperSelect(String paramString, boolean paramBoolean);

  public abstract Set<String> supportedSrcIDTypes();

  public abstract Set<String> supportedTgtIDTypes();

  public abstract boolean isMappingSupported(String paramString1, String paramString2);

  public abstract boolean mapID(Set<String> paramSet1, String paramString1, String paramString2, Set<String> paramSet2, String paramString3);

  public abstract Map<String, String> mapID(Set<String> paramSet1, String paramString, Set<String> paramSet2, Set<String> paramSet3);

  public abstract Map<String, Set<String>> mapID(Set<String> paramSet, String paramString1, String paramString2);

  public abstract boolean idExists(String paramString1, String paramString2);
}