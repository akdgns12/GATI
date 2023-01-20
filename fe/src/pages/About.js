import { useSelector, useDispatch } from "react-redux";
import { increseCount } from "../store/counter";

const About = () => {
    const dispatch = useDispatch();
    const { count } = useSelector(state => state.counter);
    
    const increase = () => {
        dispatch(increseCount());
    };
    return(
        <div>
            <h1>About Us ...</h1>
            <p> ALOHA </p>
            <div>
                {count}
                <button onClick={increase}>INCREASE</button>
            </div>
        </div>
    );
};

export default About;