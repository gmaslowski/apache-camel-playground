package com.gmaslowski.cia.event;

public enum EventType {
    CREATE {
        @Override
        public <T> T accept(EventTypeVisitor<T> eventTypeVisitor) {
            return eventTypeVisitor.visitCreate();
        }
    },

    UPDATE {
        @Override
        public <T> T accept(EventTypeVisitor<T> eventTypeVisitor) {

            return eventTypeVisitor.visitUpdate();
        }
    },

    LIST {
        @Override
        public <T> T accept(EventTypeVisitor<T> eventTypeVisitor) {

            return eventTypeVisitor.visitList();
        }
    },

    DELETE {
        @Override
        public <T> T accept(EventTypeVisitor<T> eventTypeVisitor) {

            return eventTypeVisitor.visitDelete();
        }
    };

    public abstract <T> T accept(EventTypeVisitor<T> eventTypeVisitor);

    public interface EventTypeVisitor<RETURN_TYPE> {
        RETURN_TYPE visitCreate();

        RETURN_TYPE visitUpdate();

        RETURN_TYPE visitList();

        RETURN_TYPE visitDelete();
    }


}
