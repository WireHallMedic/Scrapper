package Scrapper.Item;

public interface ItemConstants
{
   public enum QuestItem
   {
      SECURITY_KEYCARD        ("Security Keycard"),
      ENGINEERING_KEYCARD     ("Engineering Keycard"),
      TRANSIT_KEYCARD         ("Transit Keycard"),
      OPERATIONS_KEYCARD      ("Operations Keycard"),
      BRIDGE_KEYCARD          ("Bridge Keycard");
      
      public String name;
      
      private QuestItem(String n)
      {
         name = n;
      }
   }
}