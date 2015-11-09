cia-2.5
=======

Camel In Action - Examples of routing using WireTap, RecipientList

The example is implementing such flow:

![Diagram](http://yuml.me/diagram/scruffy/class/(EventsRoute)-EventRoutingProcessor>|a|,(XML files)-(EventsRoute),(EventsRoute)-WireTap(NaiveAuditTrail),|a|->(AccessingEventJmsConsumer),|a|->(ListingEventJmsConsumer),|a|->(ModifyingEventJmsConsumer),|a|->(DeletingEventJmsConsumer))