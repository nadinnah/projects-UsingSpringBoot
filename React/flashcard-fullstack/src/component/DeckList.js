import React from 'react';
import Deck from './Deck';
import { Link,useParams } from 'react-router-dom';

const DeckList = ({decks}) => {
    const{id}=useParams()

    return (
       <div >
            {decks.map(deck=>{
                 <li key={deck.id}>
                    <Link to={`/cardpage/${id}`}>
                    
                    </Link>
                </li>
                return <Deck deck={deck} key={deck.id}/>
            })}
        </div>
    );
}

export default DeckList;
