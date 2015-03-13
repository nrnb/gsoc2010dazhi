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

import cytoscape.util.plugins.communication.Message;
import cytoscape.util.plugins.communication.PluginsCommunicationSupport;
import cytoscape.util.plugins.communication.ResponseMessage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CyThesaurusServiceMessageBasedClient
  implements CyThesaurusServiceClient
{
  private final String CYTHESAURUS = "CyThesaurus";
  private final String MSG_TYPE_REQUEST_SERVICE_VERSION = "SERVICE_VERSION";
  private final String MSG_TYPE_REQUEST_SUPPORTED_ID_TYPE = "SUPPORTED_ID_TYPE";
  private final String MSG_TYPE_REQUEST_CHECK_MAPPING_SUPPORTED = "CHECK_MAPPING_SUPPORTED";
  private final String MSG_TYPE_REQUEST_ATTRIBUTE_BASED_MAPPING = "ATTRIBUTE_BASED_MAPPING";
  private final String MSG_TYPE_REQUEST_MAPPING_SERVICE = "MAPPING_SERVICE";
  private final String MSG_TYPE_REQUEST_MAPPING_SRC_CONFIG_DIALOG = "MAPPING_SRC_CONFIG_DIALOG";
  private final String MSG_TYPE_REQUEST_MAPPING_DIALOG = "MAPPING_DIALOG";
  private final String MSG_TYPE_REQUEST_MAPPERS = "ID_MAPPERS";
  private final String MSG_TYPE_REQUEST_REGISTER_MAPPER = "REGISTER_ID_MAPPER";
  private final String MSG_TYPE_REQUEST_UNREGISTER_MAPPER = "UNREGISTER_ID_MAPPER";
  private final String MSG_TYPE_REQUEST_SELECT_MAPPER = "SELECT_ID_MAPPER";
  private final String MSG_TYPE_REQUEST_ID_EXIST = "ID_EXIST";
  private final String VERSION = "VERSION";
  private final String NETWORK_ID = "NETWORK_ID";
  private final String SOURCE_ATTR = "SOURCE_ATTR";
  private final String SOURCE_ID_TYPE = "SOURCE_ID_TYPE";
  private final String MAP_TARGET_ID_TYPE_ATTR = "MAP_TARGET_ID_TYPE_ATTR";
  private final String SELECTED = "SELECTED";
  private final String CLASS_PATH = "CLASS_PATH";
  private final String CONNECTION_STRING = "CONNECTION_STRING";
  private final String DISPLAY_NAME = "DISPLAY_NAME";
  private final String SOURCE_ID = "SOURCE_ID";
  private final String SUCCESS = "SUCCESS";
  private final String IS_CANCELLED = "IS_CANCELLED";
  private final String REPORT = "REPORT";
  private final String TARGET_ID_TYPE = "TGT_ID_TYPE";
  private final String CLIENTS = "CLIENTS";
  private final String MAPPING_RESULT = "MAPPING_RESULT";
  private final String requester;

  public CyThesaurusServiceMessageBasedClient()
  {
    this(null);
  }

  public CyThesaurusServiceMessageBasedClient(String paramString)
  {
    if (paramString == null)
      this.requester = ("" + System.currentTimeMillis());
    else
      this.requester = paramString;
  }

  public boolean isServiceAvailable()
  {
    String str1 = "CyThesaurus";
    String str2 = "TEST";
    String str3 = str1 + "_" + System.currentTimeMillis();
    Message localMessage = new Message(str3, this.requester, str1, str2, null);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();
      if (localResponseMessage.getSender().compareTo(str1) == 0)
        return true;
    }
    return false;
  }

  public double serviceVersion()
  {
    String str1 = "CyThesaurus";
    super.getClass();
    String str2 = "SERVICE_VERSION";
    String str3 = this.requester + str1 + str2 + System.currentTimeMillis();
    Message localMessage = new Message(str3, this.requester, str1, str2, null);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();
      if (localResponseMessage.getSender().compareTo(str1) == 0)
      {
        Object localObject = localResponseMessage.getContent();
        if (localObject instanceof Map)
        {
          Map localMap = (Map)localObject;
          localObject = localMap.get("SUCCESS");
          if (localObject instanceof Boolean)
          {
            boolean bool = ((Boolean)localObject).booleanValue();
            if (bool)
            {
              localObject = localMap.get("VERSION");
              if (localObject instanceof Double)
                return ((Double)localObject).doubleValue();
            }
          }
        }
      }
    }
    return -1.0D;
  }

  public boolean openAttributeConfigDialog()
  {
    super.getClass();
    String str1 = "CyThesaurus";
    super.getClass();
    String str2 = "MAPPING_DIALOG";
    String str3 = this.requester + str1 + str2 + System.currentTimeMillis();
    Message localMessage = new Message(str3, this.requester, str1, str2, null);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();
      if (localResponseMessage.getSender().compareTo(str1) == 0)
        return true;
    }
    return false;
  }

  public boolean openMappingResourceConfigDialog()
  {
    super.getClass();
    String str1 = "CyThesaurus";
    super.getClass();
    String str2 = "MAPPING_SRC_CONFIG_DIALOG";
    String str3 = this.requester + str1 + str2 + System.currentTimeMillis();
    Message localMessage = new Message(str3, this.requester, str1, str2, null);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();
      if (localResponseMessage.getSender().compareTo(str1) == 0)
      {
        Object localObject = localResponseMessage.getContent();
        if (localObject instanceof Map)
        {
          Map localMap = (Map)localObject;
          localObject = localMap.get("SUCCESS");
          if (localObject instanceof Boolean)
          {
            boolean bool = ((Boolean)localObject).booleanValue();
            if (bool)
            {
              localObject = localMap.get("IS_CANCELLED");
              if (localObject instanceof Boolean)
                return !((Boolean)localObject).booleanValue();
            }
          }
        }
      }
    }
    return false;
  }

  public Set<String> allIDMappers()
  {
    String str1 = "CyThesaurus";
    super.getClass();
    String str2 = "ID_MAPPERS";
    String str3 = this.requester + str1 + str2 + System.currentTimeMillis();
    Message localMessage = new Message(str3, this.requester, str1, str2, null);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();
      if (localResponseMessage.getSender().compareTo(str1) == 0)
      {
        Object localObject = localResponseMessage.getContent();
        if (localObject instanceof Map)
        {
          Map localMap = (Map)localObject;
          localObject = localMap.get("SUCCESS");
          if (localObject instanceof Boolean)
          {
            boolean bool = ((Boolean)localObject).booleanValue();
            if (bool)
            {
              localObject = localMap.get("CLIENTS");
              if (localObject instanceof Set)
                return (Set)localObject;
            }
          }
        }
      }
    }
    return null;
  }

  public Set<String> selectedIDMappers()
  {
    String str1 = "CyThesaurus";
    super.getClass();
    String str2 = "ID_MAPPERS";
    String str3 = this.requester + str1 + str2 + System.currentTimeMillis();
    HashMap localHashMap = new HashMap();
    localHashMap.put("SELECTED", Boolean.valueOf(true));
    Message localMessage = new Message(str3, this.requester, str1, str2, localHashMap);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();
      if (localResponseMessage.getSender().compareTo(str1) == 0)
      {
        Object localObject = localResponseMessage.getContent();
        if (localObject instanceof Map)
        {
          Map localMap = (Map)localObject;
          localObject = localMap.get("SUCCESS");
          if (localObject instanceof Boolean)
          {
            boolean bool = ((Boolean)localObject).booleanValue();
            if (bool)
            {
              localObject = localMap.get("CLIENTS");
              if (localObject instanceof Set)
                return (Set)localObject;
            }
          }
        }
      }
    }
    return null;
  }

  public boolean registerIDMapper(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 == null) || (paramString2 == null))
      throw new IllegalArgumentException();
    String str1 = "CyThesaurus";
    super.getClass();
    String str2 = "REGISTER_ID_MAPPER";
    String str3 = this.requester + str1 + str2 + System.currentTimeMillis();
    HashMap localHashMap = new HashMap();
    localHashMap.put("CONNECTION_STRING", paramString1);
    localHashMap.put("CLASS_PATH", paramString2);
    localHashMap.put("DISPLAY_NAME", paramString3);
    Message localMessage = new Message(str3, this.requester, str1, str2, localHashMap);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();
      if (localResponseMessage.getSender().compareTo(str1) == 0)
      {
        Object localObject = localResponseMessage.getContent();
        if (localObject instanceof Map)
        {
          Map localMap = (Map)localObject;
          localObject = localMap.get("SUCCESS");
          if (localObject instanceof Boolean)
          {
            boolean bool = ((Boolean)localObject).booleanValue();
            if (bool)
              return true;
          }
        }
      }
    }
    return false;
  }

  public boolean unregisterIDMapper(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException();
    String str1 = "CyThesaurus";
    super.getClass();
    String str2 = "UNREGISTER_ID_MAPPER";
    String str3 = this.requester + str1 + str2 + System.currentTimeMillis();
    HashMap localHashMap = new HashMap();
    localHashMap.put("CONNECTION_STRING", paramString);
    Message localMessage = new Message(str3, this.requester, str1, str2, localHashMap);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();
      if (localResponseMessage.getSender().compareTo(str1) == 0)
      {
        Object localObject = localResponseMessage.getContent();
        if (localObject instanceof Map)
        {
          Map localMap = (Map)localObject;
          localObject = localMap.get("SUCCESS");
          if (localObject instanceof Boolean)
          {
            boolean bool = ((Boolean)localObject).booleanValue();
            if (bool)
              return true;
          }
        }
      }
    }
    return false;
  }

  public boolean setIDMapperSelect(String paramString, boolean paramBoolean)
  {
    String str1 = "CyThesaurus";
    super.getClass();
    String str2 = "SELECT_ID_MAPPER";
    String str3 = this.requester + str1 + str2 + System.currentTimeMillis();
    HashMap localHashMap = new HashMap();
    localHashMap.put("SELECTED", Boolean.valueOf(paramBoolean));
    localHashMap.put("CONNECTION_STRING", paramString);
    Message localMessage = new Message(str3, this.requester, str1, str2, localHashMap);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();
      if (localResponseMessage.getSender().compareTo(str1) == 0)
      {
        Object localObject = localResponseMessage.getContent();
        if (localObject instanceof Map)
        {
          Map localMap = (Map)localObject;
          localObject = localMap.get("SUCCESS");
          if (localObject instanceof Boolean)
          {
            boolean bool = ((Boolean)localObject).booleanValue();
            if (bool)
              return true;
          }
        }
      }
    }
    return false;
  }

  public Set<String> supportedSrcIDTypes()
  {
    String str1 = "CyThesaurus";
    super.getClass();
    String str2 = "SUPPORTED_ID_TYPE";
    String str3 = this.requester + str1 + str2 + System.currentTimeMillis();
    Message localMessage = new Message(str3, this.requester, str1, str2, null);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();
      if (localResponseMessage.getSender().compareTo(str1) == 0)
      {
        Object localObject = localResponseMessage.getContent();
        if (localObject instanceof Map)
        {
          Map localMap = (Map)localObject;
          localObject = localMap.get("SUCCESS");
          if (localObject instanceof Boolean)
          {
            boolean bool = ((Boolean)localObject).booleanValue();
            if (bool)
            {
              localObject = localMap.get("SOURCE_ID_TYPE");
              if (localObject instanceof Set)
                return (Set)localObject;
            }
            else
            {
              return null;
            }
          }
        }
      }
    }
    return null;
  }

  public Set<String> supportedTgtIDTypes()
  {
    String str1 = "CyThesaurus";
    super.getClass();
    String str2 = "SUPPORTED_ID_TYPE";
    String str3 = this.requester + str1 + str2 + System.currentTimeMillis();
    Message localMessage = new Message(str3, this.requester, str1, str2, null);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();
      if (localResponseMessage.getSender().compareTo(str1) == 0)
      {
        Object localObject = localResponseMessage.getContent();
        if (localObject instanceof Map)
        {
          Map localMap = (Map)localObject;
          localObject = localMap.get("SUCCESS");
          if (localObject instanceof Boolean)
          {
            boolean bool = ((Boolean)localObject).booleanValue();
            if (bool)
            {
              localObject = localMap.get("TGT_ID_TYPE");
              if (localObject instanceof Set)
                return (Set)localObject;
            }
            else
            {
              return null;
            }
          }
        }
      }
    }
    return null;
  }

  public boolean isMappingSupported(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return false;
    String str1 = "CyThesaurus";
    String str2 = "CHECK_MAPPING_SUPPORTED";
    String str3 = this.requester + str1 + str2 + System.currentTimeMillis();
    HashMap localHashMap = new HashMap();
    localHashMap.put("SOURCE_ID_TYPE", paramString1);
    localHashMap.put("TGT_ID_TYPE", paramString2);
    Message localMessage = new Message(str3, this.requester, str1, str2, localHashMap);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();
      if (localResponseMessage.getSender().compareTo(str1) == 0)
      {
        Object localObject = localResponseMessage.getContent();
        if (localObject instanceof Map)
        {
          Map localMap = (Map)localObject;
          localObject = localMap.get("SUCCESS");
          if (localObject instanceof Boolean)
            return ((Boolean)localObject).booleanValue();
        }
      }
    }
    return false;
  }

  public boolean mapID(Set<String> paramSet1, String paramString1, String paramString2, Set<String> paramSet2, String paramString3)
  {
    String str1 = "CyThesaurus";
    String str2 = "ATTRIBUTE_BASED_MAPPING";
    String str3 = this.requester + str1 + str2 + System.currentTimeMillis();
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("NETWORK_ID", paramSet1);
    localHashMap1.put("SOURCE_ATTR", paramString1);
    localHashMap1.put("SOURCE_ID_TYPE", paramSet2);
    HashMap localHashMap2 = new HashMap(1);
    localHashMap2.put(paramString3, paramString2);
    localHashMap1.put("MAP_TARGET_ID_TYPE_ATTR", localHashMap2);
    Message localMessage = new Message(str3, this.requester, str1, str2, localHashMap1);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();
      if (localResponseMessage.getSender().compareTo(str1) == 0)
      {
        Object localObject = localResponseMessage.getContent();
        if (localObject instanceof Map)
        {
          Map localMap = (Map)localObject;
          localObject = localMap.get("SUCCESS");
          if (localObject instanceof Boolean)
          {
            boolean bool = ((Boolean)localObject).booleanValue();
            if (bool)
              return true;
          }
        }
      }
    }
    return false;
  }

  public Map<String, String> mapID(Set<String> paramSet1, String paramString, Set<String> paramSet2, Set<String> paramSet3)
  {
    if ((paramSet2 == null) || (paramSet3 == null))
      throw new IllegalArgumentException();
    String str1 = "CyThesaurus";
    String str2 = "ATTRIBUTE_BASED_MAPPING";
    String str3 = this.requester + str1 + str2 + System.currentTimeMillis();
    HashMap localHashMap = new HashMap();
    localHashMap.put("NETWORK_ID", paramSet1);
    localHashMap.put("SOURCE_ATTR", paramString);
    localHashMap.put("SOURCE_ID_TYPE", paramSet2);
    localHashMap.put("MAP_TARGET_ID_TYPE_ATTR", paramSet3);
    Message localMessage = new Message(str3, this.requester, str1, str2, localHashMap);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();
      if (localResponseMessage.getSender().compareTo(str1) == 0)
      {
        Object localObject = localResponseMessage.getContent();
        if (localObject instanceof Map)
        {
          Map localMap = (Map)localObject;
          localObject = localMap.get("SUCCESS");
          if (localObject instanceof Boolean)
          {
            boolean bool = ((Boolean)localObject).booleanValue();
            if (bool)
            {
              localObject = localMap.get("MAP_TARGET_ID_TYPE_ATTR");
              if (localObject instanceof Map)
                return (Map)localObject;
            }
          }
        }
      }
    }
    return null;
  }

  public Map<String, Set<String>> mapID(Set<String> paramSet, String paramString1, String paramString2)
  {
    if ((paramSet == null) || (paramString1 == null) || (paramString2 == null))
      throw new IllegalArgumentException();
    String str1 = "CyThesaurus";
    super.getClass();
    String str2 = "MAPPING_SERVICE";
    String str3 = this.requester + str1 + str2 + System.currentTimeMillis();
    HashMap localHashMap = new HashMap();
    localHashMap.put("SOURCE_ID", paramSet);
    localHashMap.put("SOURCE_ID_TYPE", paramString1);
    localHashMap.put("TGT_ID_TYPE", paramString2);
    Message localMessage = new Message(str3, this.requester, str1, str2, localHashMap);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();

      if (localResponseMessage.getSender().compareTo(str1) == 0)
      {
        Object localObject = localResponseMessage.getContent();
        if (localObject instanceof Map)
        {
          Map localMap = (Map)localObject;
          localObject = localMap.get("SUCCESS");
          if (localObject instanceof Boolean)
          {
            boolean bool = ((Boolean)localObject).booleanValue();
            if (bool)
            {
              localObject = localMap.get("MAPPING_RESULT");
              if (localObject instanceof Map)
                return (Map)localObject;
            }
          }
        }
      }
    }
    return null;
  }

  public boolean idExists(String paramString1, String paramString2)
  {
    if ((paramString2 == null) || (paramString1 == null))
      throw new IllegalArgumentException();
    String str1 = "CyThesaurus";
    super.getClass();
    String str2 = "ID_EXIST";
    String str3 = this.requester + str1 + str2 + System.currentTimeMillis();
    HashMap localHashMap = new HashMap();
    localHashMap.put("SOURCE_ID", paramString1);
    localHashMap.put("SOURCE_ID_TYPE", paramString2);
    Message localMessage = new Message(str3, this.requester, str1, str2, localHashMap);
    List localList = PluginsCommunicationSupport.sendMessageAndGetResponses(localMessage);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResponseMessage localResponseMessage = (ResponseMessage)localIterator.next();
      if (localResponseMessage.getSender().compareTo(str1) == 0)
      {
        Object localObject = localResponseMessage.getContent();
        if (localObject instanceof Map)
        {
          Map localMap = (Map)localObject;
          localObject = localMap.get("SUCCESS");
          if (localObject instanceof Boolean)
          {
            boolean bool = ((Boolean)localObject).booleanValue();
            if (bool)
              return true;
          }
        }
      }
    }
    return false;
  }
}