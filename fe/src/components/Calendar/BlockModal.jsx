import { motion, AnimatePresence } from "framer-motion";
import React, { useState } from "react";
import { useRecoilState } from "recoil";
import styled from "styled-components";
import { blocksState, modalState } from "../atoms";


const DAYS_OF_WEEK = ["일", "월", "화", "수", "목", "금", "토"];
const BlockModal = () => {
  const [modalOpen, setModalOpen] = useRecoilState(modalState);
  const [blocks, setBlocks] = useRecoilState(blocksState);
  const [value, setValue] = useState("");
  const [category, setCategory] = useState("");
  const date = modalOpen.id && [
    modalOpen.id.slice(0, 4),
    modalOpen.id.slice(4, 6),
    modalOpen.id.slice(6, 8),
  ];

  const dateFormat = date && new Date(date[0], Number(date[1]) - 1, date[2]);
  const onChangeText = (e) => {
    const {
      target: { value },
    } = e;
    setValue(value);
  };
  const onChangeCategory = (e) => {
    const {
      target: { value },
    } = e;
    setCategory(value);
  };
  const addSchedule = (e) => {
    e.preventDefault();
    let newBlocks;
    if (blocks) {
      newBlocks = { ...blocks };
      if (newBlocks[modalOpen.id]) {
        newBlocks[modalOpen.id] = newBlocks[modalOpen.id].concat([
          { id: Date.now(), content: value, done: false, category },
        ]);
        console.log(newBlocks[modalOpen.id]);
      } else {
        newBlocks = {
          ...blocks,
          [modalOpen.id]: [
            { id: Date.now(), content: value, done: false, category },
          ],
        };
      }
    } else {
      newBlocks = {
        [modalOpen.id]: [
          { id: Date.now(), content: value, done: false, category },
        ],
      };
    }
    setBlocks(newBlocks);
    setValue("");
    setCategory("");
  };
  const deleteSchedule = (id) => {
    const newBlocks = { ...blocks };
    newBlocks[modalOpen.id] = newBlocks[modalOpen.id].filter(
      (value) => value.id !== id
    );
    setBlocks(newBlocks);
  };
  const doneSchecule = (id) => {
    const newBlocks = { ...blocks };
    newBlocks[modalOpen.id] = newBlocks[modalOpen.id].map((value) => {
      if (value.id === id) {
        const newObj = { ...value, done: !value.done };
        return {
          ...newBlocks[modalOpen.id],
          ...newObj,
        };
      }
    });
    setBlocks(newBlocks);
  };
  return (
    <AnimatePresence>
      <Modal
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        exit={{ opacity: 0 }}
      >
        <Overlay
          onClick={() => setModalOpen({ state: false, id: null })}
        ></Overlay>
        <ModalView>
          <div>
            {dateFormat && (
              <ModalHeader>
                {dateFormat.getMonth() + 1}월 {dateFormat.getDate()}일{" "}
                {DAYS_OF_WEEK[dateFormat.getDay()]}
              </ModalHeader>
            )}
            {modalOpen.id &&
              blocks[modalOpen.id] &&
              blocks[modalOpen.id].map((data, idx) => (
                <ModalBlock
                  initial={{ opacity: 0 }}
                  animate={{ opacity: 1 }}
                  exit={{ opacity: 0 }}
                  key={idx}
                >
                  <span>
                    <span
                      style={{ cursor: "pointer" }}
                      onClick={() => doneSchecule(data.id)}
                    >
                      {data.done ? "✅" : "⬜"}
                    </span>
                    <span style={{ marginLeft: "5px" }}>[{data.category}]</span>
                    <span style={{ marginLeft: "5px" }}>{data.content}</span>
                  </span>
                  <span
                    style={{ cursor: "pointer" }}
                    onClick={() => deleteSchedule(data.id)}
                  >
                    ❌
                  </span>
                </ModalBlock>
              ))}
          </div>
          <BlockForm onSubmit={addSchedule}>
            <BlockInput
              width={20}
              onChange={onChangeCategory}
              value={category}
              placeholder="카테고리"
            />
            <BlockInput
              width={80}
              onChange={onChangeText}
              value={value}
              placeholder="일정"
            />
            <button style={{ display: "none" }} type="submit"></button>
          </BlockForm>
        </ModalView>
      </Modal>
    </AnimatePresence>
  );
};

export default BlockModal;
const Modal = styled(motion.div)`
  position: absolute;
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;
const ModalView = styled.div`
  position: absolute;
  width: 50vw;
  height: 50vh;
  z-index: 2;
  background-color: whitesmoke;
  border-radius: 10px;
  justify-content: space-between;
  display: flex;
  flex-direction: column;
  padding: 20px;
`;
const ModalHeader = styled.div`
  font-size: 18px;
  font-weight: 600;
  margin-bottom: 10px;
`;
const Overlay = styled.div`
  position: absolute;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5);
`;
const ModalBlock = styled(motion.div)`
  flex-direction: row;
  display: flex;
  justify-content: space-between;
  background-color: whitesmoke;
  margin-block: 5px;
`;
const BlockForm = styled.form`
  display: flex;
  flex-direction: row;
`;
const BlockInput = styled.input`
  background-color: rgba(0, 0, 0, 0.05);
  border-radius: 3px;
  width: ${(p) => `${p.width}%`};
  border: none;
  padding: 5px;
  margin-inline: 5px;
  &:focus {
    outline: none;
  }
`;