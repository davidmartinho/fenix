/**
 * Copyright © 2002 Instituto Superior Técnico
 *
 * This file is part of FenixEdu Core.
 *
 * FenixEdu Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu Core.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.fenixedu.domain.messaging;

import jvstm.cps.ConsistencyPredicate;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;

import org.joda.time.DateTime;

import pt.utl.ist.fenix.tools.util.i18n.MultiLanguageString;

public class ConversationThread extends ConversationThread_Base implements Comparable<ConversationThread> {

    public ConversationThread(Forum forum, Person creator, MultiLanguageString subject) {
        setCreationDate(new DateTime());
        init(forum, creator, subject);
    }

    private void init(Forum forum, Person creator, MultiLanguageString subject) {
        setForum(forum);
        setTitle(subject);
        setCreator(creator);

    }

    @Override
    public void setForum(Forum forum) {
        if (forum == null) {
            throw new DomainException("conversationThread.forum.cannot.be.null");
        }
        super.setForum(forum);
    }

    @Override
    public void setTitle(MultiLanguageString subject) {
        if (subject == null || subject.getContent() == null || subject.getContent().trim().isEmpty()) {
            throw new DomainException("conversationThread.subject.cannot.be.null");
        }
        super.setTitle(subject);
    }

    @Override
    public void setCreator(Person creator) {
        if (creator == null) {
            throw new DomainException("conversationThread.creator.cannot.be.null");
        }
        super.setCreator(creator);
    }

    public void checkIfPersonCanWrite(Person person) {
        getForum().checkIfPersonCanWrite(person);
    }

    public ConversationMessage createConversationMessage(Person creator, MultiLanguageString body) {
        checkIfPersonCanWrite(creator);
        return new ConversationMessage(this, creator, body);
    }

    public ConversationMessage getNextToLastConversationMessage() {
        ConversationMessage lastConversationMessage = null;
        ConversationMessage nextToLastConversationMessage = null;

        for (ConversationMessage conversationMessage : getMessageSet()) {
            if (lastConversationMessage == null) {
                lastConversationMessage = conversationMessage;
            } else if (conversationMessage.getCreationDate().compareTo(lastConversationMessage.getCreationDate()) > 1) {
                nextToLastConversationMessage = lastConversationMessage;
                lastConversationMessage = conversationMessage;
            } else if (nextToLastConversationMessage == null) {
                nextToLastConversationMessage = conversationMessage;
            } else if (conversationMessage.getCreationDate().compareTo(nextToLastConversationMessage.getCreationDate()) > 1) {
                nextToLastConversationMessage = conversationMessage;
            }
        }

        return nextToLastConversationMessage;
    }

    public void addConversationMessages(ConversationMessage conversationMessage) {
        conversationMessage.setConversationThread(this);
    }

    @ConsistencyPredicate
    public final boolean checkTitle() {
        return getTitle() != null && getTitle().getContent() != null && !getTitle().getContent().trim().isEmpty();
    }

    public void delete() {
        for (final ConversationMessage message : getMessageSet()) {
            message.delete();
        }
        setCreator(null);
        setForum(null);
        deleteDomainObject();
    }

    @Override
    public int compareTo(ConversationThread o) {
        return this.getCreationDate().compareTo(o.getCreationDate());
    }

}