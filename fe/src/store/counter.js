export const INCRESE = "COUNT/INCRESE";

export const increseCount = count => ({ type: INCRESE, count });

const initalState = {
    count: 0
};

const counter = (state = initalState, action) => {
    switch (action.type) {
        case INCRESE:
            return {
                ...state,
                count: state.count + 1,
            };

        default:
            return state;
    }
};

export default counter;